import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {Observable, Subject} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly api: string = "/api/auth/"
    private _token: string = "";
    private _isLogin: boolean = false;
    private lastDate: Date | undefined;
    private _username: string = "";

    constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
        this._token = this.cookieService.get('token');
        this._username = this.cookieService.get('username');
    }

    public getToken(username: string, password: string): Subject<boolean> {
        const subject = new Subject<boolean>()

        this.http.post<{ token: string; username: string; last_date: Date }>(this.api + "login", {
                username,
                password
            },
            {
                responseType: 'json'
            }
        ).subscribe({
            next: value => {
                this._isLogin = true
                this._token = value.token
                this.lastDate = value.last_date;
                this._username = value.username;
                this.cookieService.set('token', value.token)
                this.cookieService.set('username', value.username)
                subject.next(true)
                subject.complete()
            },
            error: (err: any) => {
                console.error(err)
                this._isLogin = false;
                this._token = ""
                this._username = ""
                this.lastDate = undefined;
                this.cookieService.delete("token")
                this.cookieService.delete('username')
                subject.next(false);
                subject.complete()
            }
        })
        return subject
    }

    public check(): Observable<boolean> {
        const subject = new Subject<boolean>()
        this.http.get('api/auth/check').subscribe({
            next: () => {
                subject.next(true);
                subject.complete();
            },
            error: err => {
                console.error(err)
                subject.next(false);
                subject.complete()
            }
        })
        return subject;
    }

    public logout(): void {
        this._isLogin = false;
        this._token = ""
        this._username = ""
        this.lastDate = undefined;
        this.cookieService.delete("token")
        this.cookieService.delete('username')
        this.router.navigate(['/login']);
    }

    public register(firstName: string | null,
                    secondName: string | null,
                    surname: string | null,
                    username: string | null,
                    email: string | null,
                    password: string | null): void {
        this.http.post('api/profile/create', {
            firstName,
            secondName,
            surname,
            username,
            email,
            password
        }, {responseType: 'text'}).subscribe({
            next: value => {
                // @ts-ignore
                this.getToken(username, password).subscribe({
                    next: value1 => {
                        if (value1) {
                            this.router.navigate(['/profile']);
                        }
                    }
                })
            },
            error: () => {
                alert("Повторяющиеся значение в логине или почте")
            }
        })
    }

    get token(): string {
        return this._token;
    }

    get isLogin(): boolean {
        return this._isLogin;
    }


    set isLogin(value: boolean) {
        this._isLogin = value;
    }

    get username(): string {
        return this._username;
    }
}
