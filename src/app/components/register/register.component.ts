import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    profileForm = new FormGroup({
        username: new FormControl('', [Validators.required]),
        first_name: new FormControl('', [Validators.required]),
        second_name: new FormControl('', [Validators.required]),
        surname: new FormControl('', [Validators.required]),
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', [Validators.required]),
    })

    constructor(private authService: AuthService) {
    }

    public register(): void {
        this.authService.register(
            this.profileForm.controls.first_name.value,
            this.profileForm.controls.second_name.value,
            this.profileForm.controls.surname.value,
            this.profileForm.controls.username.value,
            this.profileForm.controls.email.value,
            this.profileForm.controls.password.value
        )
    }
}
