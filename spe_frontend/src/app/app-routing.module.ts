import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { EstabelecimentoModule } from './view/estabelecimento/estabelecimento.module';
import { ProfissionalModule } from './view/profissional/profissional.module';
const routes: Routes = [
    { path: 'login-sucess', component: LoginSuccessComponent },
    {
      path: 'estabelecimento',
      loadChildren: () => EstabelecimentoModule
    },
    {
      path: 'profissional',
      loadChildren: () => ProfissionalModule
    },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
