import {Component, Input, OnInit} from '@angular/core';
import {Message} from "../model/message";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {MessageService} from "../../services/message/message.service";

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

    constructor(private messageService: MessageService, public route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.data.subscribe(value => {
            if (value['pageName'] === 'feed')
                this.messageService.getAll().subscribe({
                    next: value => {
                        this.messages = value;
                    },
                    error: err => {
                        console.error(err)
                    }
                })
            else
                this.messageService.getRecommended().subscribe({
                    next: value => {
                        this.messages = value;
                    },
                    error: err => {
                        console.error(err)
                    }
                })
        })
    }

    reload(event: boolean) {
        if (event) this.ngOnInit();
    }
}
