import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../model/message";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

export type PageName = 'feed' | 'customFeed'

@Component({
    selector: 'feed',
    templateUrl: './feed.component.html',
    styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {
    @Input()
    public pageName: PageName = 'feed'
    public messages: Message[] | undefined;

    constructor(private http: HttpClient, private route: ActivatedRoute, private authService: AuthService) {

    }

    ngOnInit(): void {
        this.route.data.subscribe(value => {
            if (value['pageName'] === 'feed') {
                this.http.get<Message[]>('/api/message/getAll').subscribe({
                    next: value => {
                        this.messages = value;
                    },
                    error: err => {
                        console.error(err)
                    }
                })
            } else {
                this.http.get<Message[]>('/api/message/getRecommended', {
                    params: {
                        username: this.authService.username
                    }
                }).subscribe({
                    next: value => {
                        this.messages = value;
                    },
                    error: err => {
                        console.error(err)
                    }
                })
            }
        })
    }

    reload(event: boolean) {
        if (event)
            this.ngOnInit();
    }
}
