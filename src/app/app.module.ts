import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SharedModule} from "./shared/shared.module";
import {ProfileComponent} from './components/profile/profile.component';
import {AppRoutingModule} from "./app-routing.module";
import {FeedComponent} from './components/feed/feed.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {TokenInterceptor} from "./interceptors/token/token.interceptor";
import {CookieService} from "ngx-cookie-service";
import {MessageComponent} from './components/message/message.component';
import { CreateMessageComponent } from './components/create-message/create-message.component';

@NgModule({
    declarations: [
        AppComponent,
        ProfileComponent,
        FeedComponent,
        RegisterComponent,
        LoginComponent,
        MessageComponent,
        CreateMessageComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        SharedModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,

    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
        CookieService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
