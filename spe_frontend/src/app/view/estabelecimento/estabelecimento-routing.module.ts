import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormEstabelecimentoComponent } from './form-estabelecimento/form-estabelecimento.component';
import { ListarEstabelecimentoComponent } from './listar-estabelecimento/listar-estabelecimento.component';
const routes: Routes = [
    {
      path: 'listar',
      component: ListarEstabelecimentoComponent
    },
    {
      path: 'cadastrar',
      component: FormEstabelecimentoComponent
    },
    {
      path: 'editar/:id',
      component: FormEstabelecimentoComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EstabelecimentoRoutingModule { }
