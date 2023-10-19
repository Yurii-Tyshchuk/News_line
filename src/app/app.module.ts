import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {SharedModule} from "./shared/shared.module";
import { ProfileComponent } from './components/profile/profile.component';
import {AppRoutingModule} from "./app-routing.module";
import { FeedComponent } from './components/feed/feed.component';

@NgModule({
    declarations: [
        AppComponent,
        ProfileComponent,
        FeedComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        SharedModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
