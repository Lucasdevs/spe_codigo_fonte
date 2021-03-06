import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormProfissionalComponent } from './form-profissional/form-profissional.component';
import { ListarProfissionalComponent } from './listar-profissional/listar-profissional.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProfissionalRoutingModule } from './profissional-routing.module';
import { CardAdicionarModule } from 'src/app/components/card-adicionar/card-adicionar.module';
import { ProfissionalService } from 'src/app/shared/service/profissional.service';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';

@NgModule({
  declarations: [
    FormProfissionalComponent,
    ListarProfissionalComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ProfissionalRoutingModule,
    CardAdicionarModule
  ],
  providers: [
    ProfissionalService,
    EstabelecimentoService
  ]
})
export class ProfissionalModule { }
