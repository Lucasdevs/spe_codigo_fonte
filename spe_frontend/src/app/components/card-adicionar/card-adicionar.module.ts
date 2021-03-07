import { NgModule } from '@angular/core';
import { CardAdicionarComponent } from "./card-adicionar.component";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { CardModule } from "primeng";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    CardModule
  ],
  declarations: [
    CardAdicionarComponent
  ],
  exports: [
    CardAdicionarComponent
  ]
})
export class CardAdicionarModule {
}
