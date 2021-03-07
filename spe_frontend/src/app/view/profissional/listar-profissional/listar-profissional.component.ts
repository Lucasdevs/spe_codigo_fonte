import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ConfirmationService, MessageService, SelectItem, Table } from 'primeng';
import { Page } from 'src/app/domain/page';
import { Profissional } from 'src/app/domain/profissional';
import { BaseController } from 'src/app/shared/controller/base-controller';
import { ProfissionalService } from 'src/app/shared/service/profissional.service';
import { finalize } from 'rxjs/operators';
import { ConstantsUtil } from 'src/app/util/constants-util';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';
import { Estabelecimento } from 'src/app/domain/estabelecimento';


@Component({
  selector: 'app-listar-profissional',
  templateUrl: './listar-profissional.component.html',
  styleUrls: ['./listar-profissional.component.css'],
  providers: [ConfirmationService]
})
export class ListarProfissionalComponent extends BaseController implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild('tabela') datatable: Table;

  profissional = new Profissional();
  estabelecimento = new Estabelecimento();
  estabelecimentos: Page<Estabelecimento> = new Page;
  profissionais: Page<Profissional> = new Page;
  travarCarregamentoTabela: boolean;
  exibeDialog = false;
  estabelecimentosVaziaSelect: SelectItem[] = [];
  estabelecimentosSelect: SelectItem[] = [];
  verificafiltro = true;

  colunas = [
    { field: 'nome', header: 'Nome' },
    { field: 'endereco', header: 'Endereço' },
    { field: 'celular', header: 'Celular' },
    { field: 'telefone' , header: 'Telefone'},
    { field: 'funcao' , header: 'Função'},
  ];

  constructor(
    private router: Router,
    private profissionalService: ProfissionalService,
    private estabelecimentoService: EstabelecimentoService,
    private confirmationService: ConfirmationService,
    messageService: MessageService) {

    super(messageService);
    }

  ngOnInit(): void {
  }

  listar() {
      this.blockUI.start(ConstantsUtil.BUSCANDO);
      this.profissionalService.listar(this.profissional, this.datatable)
        .pipe(finalize(() => this.blockUI.stop()))
        .subscribe((response) => {
          this.profissionais = response;
        });
      this.travarCarregamentoTabela = true;
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
        this.profissionalService.excluir(id)
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

  listarEstabelecimentos() {
      this.blockUI.start(ConstantsUtil.BUSCANDO);
      this.estabelecimentoService.listar(this.estabelecimento, this.datatable)
        .pipe(finalize(() => this.blockUI.stop()))
        .subscribe((response) => {
          if (this.verificafiltro) {
          this.estabelecimentos = response;
          this.estabelecimentos.content.forEach(element => {
            this.estabelecimentosSelect = this.estabelecimentosSelect.concat({value: element.id, label: element.nome});
            this.verificafiltro = false;
          });
        }
      });
  }
  cadastrar() {
    this.router.navigate(['profissional/cadastrar']);
  }

  editar(id: number) {
    this.router.navigate([`/profissional/editar/${id}`]);
  }

  travarCarregamento(event) {
    this.travarCarregamentoTabela = event;
  }

  abrirModal(id: number) {
    this.obterPorId(id);
    this.listarEstabelecimentos();
    this.exibeDialog = true;
  }

  obterPorId(id: number) {
    this.blockUI.start();
    this.profissionalService.buscarPorId(id)
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe((response) => {
        this.preencherModel(response);
      });
  }

  obterPorIdEstabelecimento(id: number) {
    this.blockUI.start();
    this.estabelecimentoService.buscarPorId(id)
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe((response) => {
        this.preencherModelEstab(response);
      });
  }

  editarE() {
    this.estabelecimentoService.editar(this.estabelecimento)
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe(() => {
          this.montarMsgSucesso(ConstantsUtil.OPERACAO_SUCESSO);
        },
        erro => {
          this.montarMsgAlerta(erro.error.detail);
        });
}


  salvarAdd() {
    this.fecharModal();

    this.estabelecimentosVaziaSelect.forEach(n => {
      this.obterPorIdEstabelecimento(n.value);
      this.estabelecimento.idProfissional = this.profissional.id;
      this.editarE();
    });
    this.limpar();
    this.montarMsgSucesso(ConstantsUtil.OPERACAO_SUCESSO);
  }


  private preencherModel(response) {
    this.profissional = response;
  }

  private preencherModelEstab(response) {
    this.estabelecimento = response;
  }

  fecharModal() {
    this.exibeDialog = false;
  }

  liberaCarregamento() {
    this.travarCarregamentoTabela = false;
    this.listar();
  }

  limpar() {
    this.profissional = new Profissional();
    this.datatable.reset();
  }
}
