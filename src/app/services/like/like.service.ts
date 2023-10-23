import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";
import {Observable, of} from "rxjs";
import {Like} from "../../components/model/like";

@Injectable({
    providedIn: 'root'
})
export class LikeService {
    constructor(private http: HttpClient, private authService: AuthService) {
    }

    public change(id: number, change: boolean): Observable<any> {
        return this.http.get('/api/like/change', {
            params: {
                message_id: id,
                username: this.authService.username,
                change
            }
        })
    }

    public getAllByUsername(): Observable<Like[]> {
        if (this.authService.username)
            return this.http.get<Like[]>('/api/like/getMy', {
                params: {
                    username: this.authService.username
                }
            })
        else return of([])
    }
}
