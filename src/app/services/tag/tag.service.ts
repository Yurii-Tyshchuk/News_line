import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Tag} from "../../components/model/tag";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TagService {

    constructor(private http: HttpClient) {
    }

    public getAll(): Observable<Tag[]> {
        return this.http.get<Tag[]>('/api/tag/getAll');
    }

    public createTag(tagName: string): Observable<Tag> {
        return this.http.get<Tag>('/api/tag/create', {
            params: {
                name: tagName
            }
        })
    }
}
