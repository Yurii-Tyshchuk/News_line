import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Message} from "../model/message";
import {faHeart, faHeartBroken} from "@fortawesome/free-solid-svg-icons";
import {LikeService} from "../../services/like/like.service";


@Component({
    selector: 'message',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.scss']
})
export class MessageComponent {
    @Output()
    likeIsChanged = new EventEmitter<boolean>()
    faHeart = faHeart
    faHeartBroken = faHeartBroken
    @Input()
    public message!: Message
    private buffer: { id: number }[] = []

    constructor(private likeService: LikeService) {
        this.getAllByUsername();
    }

    private getAllByUsername(): void {
        this.likeService.getAllByUsername().subscribe({
            next: value => {
                if (value) {
                    this.buffer = []
                    for (let like1 of value)
                        this.buffer.push(like1)
                }
            }
        })
    }

    public like(message: Message): void {
        let flag = false;
        if (message.likes) {
            let flag2 = true;
            for (let like of message.likes) {
                if (this.buffer.find(value => value.id === like.id)) {
                    flag = false;
                    flag2 = false;
                    break;
                }
            }
            if (flag2)
                flag = true;
        } else {
            flag = true;
        }

        this.likeService.change(message.id, flag).subscribe({
            next: () => {
                this.likeIsChanged.emit(true)
            },
        })
    }
}
