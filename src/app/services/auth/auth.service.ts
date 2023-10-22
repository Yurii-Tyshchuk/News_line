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
