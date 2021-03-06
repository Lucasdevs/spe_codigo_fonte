import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormProfissionalComponent } from './form-profissional/form-profissional.component';
import { ListarProfissionalComponent } from './listar-profissional/listar-profissional.component';
const routes: Routes = [
    {
      path: 'listar',
      component: ListarProfissionalComponent
    },
    {
      path: 'cadastrar',
      component: FormProfissionalComponent
    },
    {
      path: 'editar/:id',
      component: FormProfissionalComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfissionalRoutingModule { }
