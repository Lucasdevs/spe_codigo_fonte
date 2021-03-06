import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ConfirmationService, MessageService, Table } from 'primeng';
import { Page } from 'src/app/domain/page';
import { BaseController } from 'src/app/shared/controller/base-controller';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';
import { finalize } from 'rxjs/operators';
import { ConstantsUtil } from 'src/app/util/constants-util';
import { Estabelecimento } from 'src/app/domain/estabelecimento';

@Component({
  selector: 'app-listar-estabelecimento',
  templateUrl: './listar-estabelecimento.component.html',
  styleUrls: ['./listar-estabelecimento.component.css'],
  providers: [ConfirmationService]
})
export class ListarEstabelecimentoComponent extends BaseController implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild('tabela') datatable: Table;

  estabelecimento = new Estabelecimento();
  estabelecimentos: Page<Estabelecimento> = new Page;
  travarCarregamentoTabela: boolean;
  exibeDialog = false;

  colunas = [
    { field: 'nome', header: 'Nome' },
    { field: 'endereco', header: 'Endereço' },
    { field: 'telefone' , header: 'Telefone'}
  ];

  constructor(
    private router: Router,
    private estabelecimentoService: EstabelecimentoService,
    private confirmationService: ConfirmationService,
    messageService: MessageService) {

    super(messageService);
    }

  ngOnInit(): void {
  }

  listar() {
    if (!this.travarCarregamentoTabela) {
      this.blockUI.start(ConstantsUtil.BUSCANDO);
      this.estabelecimentoService.listar(this.estabelecimento, this.datatable)
        .pipe(finalize(() => this.blockUI.stop()))
        .subscribe((response) => {
          this.estabelecimentos = response;
        });
      this.travarCarregamentoTabela = true;
    }
  }

  excluir(id: number) {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este item?',
      acceptLabel: 'Sim',
      rejectLabel: 'Não',
      acceptButtonStyleClass: 'ui-button-success',
      rejectButtonStyleClass: 'ui-button-danger',
      accept: () => {
        this.blockUI.start(ConstantsUtil.EXCLUINDO);
        this.estabelecimentoService.excluir(id)
          .pipe(finalize(() => this.blockUI.stop()))
          .subscribe(() => {
            this.limpar();
            this.liberaCarregamento();
            this.montarMsgSucesso(ConstantsUtil.OPERACAO_SUCESSO);
          },
            erro => {
              this.montarMsgAlerta(erro.error.title);
            });
      }
    });
  }

  cadastrar(){
    this.router.navigate(['estabelecimento/cadastrar']);
  }

  editar(id: number) {
    this.router.navigate([`/estabelecimento/editar/${id}`]);
  }

  travarCarregamento(event) {
    this.travarCarregamentoTabela = event;
  }

  liberaCarregamento() {
    this.travarCarregamentoTabela = false;
    this.listar();
  }

  limpar(){
    this.estabelecimento = new Estabelecimento();
    this.datatable.reset();
  }

}
