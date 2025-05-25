import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../../components/model/message";
import {AuthService} from "../auth/auth.service";
import {Tag} from "../../components/model/tag";

@Injectable({
    providedIn: 'root'
})
export class MessageService {
    constructor(private http: HttpClient, private authService: AuthService) {
    }

    public getAll(): Observable<Message[]> {
        return this.http.get<Message[]>('/api/message/getAll');
    }

    public getRecommended(): Observable<Message[]> {
        return this.http.get<Message[]>('/api/message/getRecommended', {
            params: {
                username: this.authService.username
            }
        })
    }

    public getByTextAndTags(text: string, tags: Tag[] = []): Observable<Message[]> {
        if (text === '' && tags.length === 0) {
            return this.getAll();
        }

        return this.http.post<Message[]>('/api/message/getByTextAndTags', {
            text,
            tags
        })
    }
}
