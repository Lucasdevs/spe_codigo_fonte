import { Component, Attribute, Input } from '@angular/core';

@Component({
  selector: 'app-card-adicionar',
  templateUrl: './card-adicionar.component.html',
  styleUrls: ['./card-adicionar.component.css']
})
export class CardAdicionarComponent {

  @Input() custom = '';
  constructor(
    @Attribute('label') public label: string = '',
    @Attribute('routerLink') public routerLink: string = '',
  ) { }

}
