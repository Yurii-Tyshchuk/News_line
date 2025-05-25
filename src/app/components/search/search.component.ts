import {Component, OnInit} from '@angular/core';
import {Message} from "../model/message";
import {FormControl, FormGroup} from "@angular/forms";
import {TagService} from "../../services/tag/tag.service";
import {Tag} from "../model/tag";
import {MessageService} from "../../services/message/message.service";

@Component({
    selector: 'search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
    messages: Message[] | undefined;
    allTags: Tag[] = [];
    searchForm = new FormGroup({
        text: new FormControl(''),
        tags: new FormControl<string[]>([]),
    })

    constructor(private tagService: TagService, private messageService: MessageService) {
        this.messageService.getAll().subscribe({
            next: value => {
                this.messages = value;
            },
            error: err => {
                console.error(err)
            }
        })
    }

    ngOnInit(): void {
        this.tagService.getAll().subscribe({
            next: value => {
                this.allTags = value
            },
            error: err => {
                console.error(err)
            }
        })
    }

    reload(event: boolean) {
        if (event) {
            this.ngOnInit();
            this.search();
        }
    }

    search(): void {
        if (this.searchForm.valid) {
            const text = this.searchForm.controls.text.value;
            const tags = this.searchForm.controls.tags.value;
            // @ts-ignore
            this.messageService.getByTextAndTags(text, tags).subscribe({
                next: value => {
                    this.messages = value;
                },
                error: err => {
                    console.error(err)
                }
            })
        }
    }
}
