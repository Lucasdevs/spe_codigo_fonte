import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';
import { ProfissionalService } from 'src/app/shared/service/profissional.service';



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    HomeRoutingModule
  ],
  providers:[
    EstabelecimentoService,
    ProfissionalService
  ]
})
export class HomeModule { }
