import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfileComponent} from "./components/profile/profile.component";
import {AuthGuard} from "./services/auth/auth.guard";
import {FeedComponent} from "./components/feed/feed.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {CreateMessageComponent} from "./components/create-message/create-message.component";
import {SearchComponent} from "./components/search/search.component";

const routes: Routes = [
    {
        path: 'profile',
        title: 'Профиль',
        component: ProfileComponent,
        data: {
            pageName: 'profile'
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'feed',
        title: 'Новости',
        component: FeedComponent,
        data: {
            pageName: 'feed'
        }
    },
    {
        path: 'customFeed',
        title: 'Рекомендации',
        component: FeedComponent,
        data: {
            pageName: 'customFeed'
        },
        canActivate: [AuthGuard]
    },
    {
        path: '',
        title: 'Новости',
        component: FeedComponent,
        data: {
            pageName: 'feed'
        }
    },
    {
        path: 'register',
        title: 'Регистрация',
        component: RegisterComponent,
        data: {
            pageName: 'register'
        }
    },
    {
        path: 'login',
        title: 'Логин',
        component: LoginComponent,
        data: {
            pageName: 'login'
        }
    },
    {
        path: 'search',
        title: 'Поиск',
        component: SearchComponent,
        data: {
            pageName: 'search'
        }
    },
    {
        path: 'create',
        title: 'Создание новости',
        component: CreateMessageComponent,
        data: {
            pageName: 'create-message'
        },
        canActivate: [AuthGuard]
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
        useHash: true,
        onSameUrlNavigation: 'reload'
    })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
