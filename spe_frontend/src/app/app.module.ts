import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SecurityModule, VersionTagModule } from '@nuvem/angular-base';
import { BreadcrumbModule, ErrorStackModule, MenuModule, PageNotificationModule } from '@nuvem/primeng-components';
import { BlockUIModule } from 'ng-block-ui';
import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppFooterComponent } from './components/footer/app.footer.component';
import { MecMenuComponent } from './components/mec-menu/mec-menu.component';
import { AppTopbarComponent } from './components/topbar/app.topbar.component';
import { SharedModule } from './shared/shared.module';
import { CardAdicionarModule } from './components/card-adicionar/card-adicionar.module';
import {MultiSelectModule} from 'primeng/multiselect';
import { EstabelecimentoModule } from './view/estabelecimento/estabelecimento.module';
import { ProfissionalModule } from './view/profissional/profissional.module';


@NgModule({
    declarations: [
        AppComponent,
        AppTopbarComponent,
        AppFooterComponent,   
        MecMenuComponent
    ],
    imports: [
        MultiSelectModule,
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        SharedModule,
        HttpClientModule,
        PageNotificationModule,
        BreadcrumbModule,
        ErrorStackModule,
        VersionTagModule,
        SecurityModule.forRoot(environment.auth),
        MenuModule,
        BlockUIModule.forRoot({
            message: 'Carregando'
        }),
        CardAdicionarModule,
        EstabelecimentoModule,
        ProfissionalModule,
        SharedModule
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy}
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
