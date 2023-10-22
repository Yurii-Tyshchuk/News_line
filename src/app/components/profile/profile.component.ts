import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile/profile.service";
import {User} from "../model/user";

@Component({
    selector: 'profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    public user: User | undefined

    constructor(private profileService: ProfileService) {
    }

    ngOnInit(): void {
        this.profileService.getProfileInfo().subscribe(value => {
            console.log(value)
            this.user = value;
        })
    }

    public reload(event: boolean): void {
        if (event) this.ngOnInit();
    }
}
