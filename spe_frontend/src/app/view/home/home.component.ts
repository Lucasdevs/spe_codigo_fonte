import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Estabelecimento } from 'src/app/domain/estabelecimento';
import { Page } from 'src/app/domain/page';
import { Profissional } from 'src/app/domain/profissional';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';
import { ProfissionalService } from 'src/app/shared/service/profissional.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  data:any;
  numEstabelecimentos: any;
  numProfissionais: any;
  profissional = new Profissional();
  estabelecimento = new Estabelecimento();
  profissionais: Page<Profissional> = new Page;
  estabelecimentos: Page<Estabelecimento> = new Page;
  @ViewChild('tabela') datatablePro: Table;
  @ViewChild('tabela') datatableEsta: Table

    constructor(
      private profissionalService: ProfissionalService,
      private estabelecimentoService: EstabelecimentoService
    ) 
    {}
   

  ngOnInit(): void {
    this.listarProfissionais();
    this.listarEstabelecimentos();
  }

  listarProfissionais() {
    this.profissionalService.listar(this.profissional, this.datatablePro)
      .subscribe((response) => {
        this.profissionais = response;
        this.numProfissionais = this.profissionais.totalElements;
        this.show()
      });
  }

  listarEstabelecimentos() {
    this.estabelecimentoService.listar(this.estabelecimento, this.datatableEsta)
      .subscribe((response) => {
        this.estabelecimentos = response;
        this.numEstabelecimentos = this.estabelecimentos.totalElements;
        this.show()
      });
  }

  show(){
    console.log(this.numEstabelecimentos);
    console.log(this.numProfissionais);
    
    this.data = {
      labels: ['Estabelecimentos','Profissionais'],
      datasets: [
          {
              data: [this.numEstabelecimentos, this.numProfissionais],
              backgroundColor: [
                  "#0F0E0EDA",
                  "#009688",
              ],
              hoverBackgroundColor: [
                  "#0F0E0EDA",
                  "#009688",
              ]
          }]    
      };
  }
}
