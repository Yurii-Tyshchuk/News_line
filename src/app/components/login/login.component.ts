import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    profileForm = new FormGroup({
        login: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required]),
    })

    constructor(private authService: AuthService, private router: Router) {
    }

    public login(): void {
        if (this.profileForm.valid) {
            this.authService.getToken(
                <string>this.profileForm.value.login,
                <string>this.profileForm.value.password
            ).subscribe({
                next: value => {
                    if (value)
                        this.router.navigate(["/profile"])
                }
            })
        }
    }

    public redirectToRegister(): void {
        this.router.navigate(['/register']);
    }
}
