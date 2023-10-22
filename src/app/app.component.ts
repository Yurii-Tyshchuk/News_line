import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from "./services/auth/auth.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(private http: HttpClient, private authService: AuthService) {
        this.http.get('api/auth/check').subscribe({
            next: () => {
                this.authService.isLogin = true;
            },
            error: err => {
                console.error(err)
                this.authService.isLogin = false;
            }
        })
    }
}
