import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";
import {Observable} from "rxjs";
import {User} from "../../components/model/user";

@Injectable({
    providedIn: 'root'
})
export class ProfileService {
    constructor(private http: HttpClient, private authService: AuthService) {
    }

    public getProfileInfo(): Observable<User> {
        return this.http.get<User>("/api/profile/getProfileInfo", {responseType: "json"})
    }
}
