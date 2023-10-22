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

    constructor(private likeService: LikeService) {
    }

    public dislike(id: number): void {
        this.likeService.change(id, false).subscribe({
            next: () => {
                this.likeIsChanged.emit(true)
            },
        })
    }

    public like(id: number): void {
        this.likeService.change(id, true).subscribe({
            next: () => {
                this.likeIsChanged.emit(true)
            },
        })
    }
}
