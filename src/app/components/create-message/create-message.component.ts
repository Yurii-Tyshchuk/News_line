import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Tag} from "../model/tag";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: 'create-message',
    templateUrl: './create-message.component.html',
    styleUrls: ['./create-message.component.scss']
})
export class CreateMessageComponent {
    public allTags: Tag[] = [];
    messageForm = new FormGroup({
        header: new FormControl('', [Validators.required]),
        text: new FormControl('', [Validators.required]),
        tags: new FormControl<string[]>([], [Validators.required]),
    })
    newTag: string | undefined;

    constructor(private http: HttpClient, private authService: AuthService, private router: Router) {
        this.getAll();
    }

    public getAll(): void {
        this.http.get<Tag[]>('/api/tag/getAll').subscribe({
            next: value => {
                this.allTags = value;
                this.newTag = '';
            },
            error: err => {
                console.error(err)
            }
        })
    }

    public createTag(): void {
        if (this.newTag)
            this.http.get('/api/tag/create', {
                params: {
                    name: this.newTag
                }
            }).subscribe({
                next: () => {
                    this.getAll()
                },
                error: err => {
                    alert("Не уникальное имя")
                    console.error(err)
                }
            })
    }

    public createMessage() {
        if (this.messageForm.controls.tags.value)
            this.http.post('/api/message/create', {
                header: this.messageForm.controls.header.value,
                text: this.messageForm.controls.text.value,
                username: this.authService.username,
                tag: this.allTags.filter(tag =>
                    this.messageForm.controls.tags.value?.includes(tag.name)
                )
            }, {responseType: 'text'}).subscribe({
                next: () => {
                    this.router.navigate(['/profile'])
                },
                error: err => {
                    console.error(err)
                }
            })
    }
}
