import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";
import {Observable} from "rxjs";

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
}
