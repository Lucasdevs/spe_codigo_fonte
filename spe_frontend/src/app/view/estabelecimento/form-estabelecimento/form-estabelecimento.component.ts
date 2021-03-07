import { Component, Input, OnInit } from '@angular/core';
import { BaseController } from 'src/app/shared/controller/base-controller';
import { ConstantsUtil } from 'src/app/util/constants-util';
import { ActivatedRoute, Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { MessageService } from 'primeng';
import { finalize } from 'rxjs/operators';
import { EstabelecimentoService } from 'src/app/shared/service/estabelecimento.service';
import { Estabelecimento } from 'src/app/domain/estabelecimento';

@Component({
  selector: 'app-form-estabelecimento',
  templateUrl: './form-estabelecimento.component.html',
  styleUrls: ['./form-estabelecimento.component.css']
})
export class FormEstabelecimentoComponent extends BaseController implements OnInit {

  estabelecimento = new Estabelecimento();

  @Input() titulo;

  @BlockUI() blockUI: NgBlockUI;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private estabelecimentoService: EstabelecimentoService,
    messageService: MessageService) {

    super(messageService);
    }

    ngOnInit(){
      this.verificaIdRota();
    }
    salvar(form) {
      if (!form.valid) {
        return;
      }
      if (this.estabelecimento.id) {
        this.editar();
      } else {
        this.cadastrar();
      }
    }
  
    cadastrar() {
      this.blockUI.start(ConstantsUtil.SALVANDO);
      this.estabelecimentoService.salvar(this.estabelecimento)
        .pipe(finalize(() => this.blockUI.stop()))
        .subscribe(() => {
            this.router.navigate(['/estabelecimento/listar']);
            this.montarMsgSucesso(ConstantsUtil.OPERACAO_SUCESSO);
          },
          erro => {
            this.montarMsgAlerta(erro.error.detail);
          });
    }
  
    editar() {
        this.blockUI.start(ConstantsUtil.SALVANDO);
        this.estabelecimentoService.editar(this.estabelecimento)
          .pipe(finalize(() => this.blockUI.stop()))
          .subscribe(() => {
              this.router.navigate(['/estabelecimento/listar']);
              this.montarMsgSucesso(ConstantsUtil.OPERACAO_SUCESSO);
            },
            erro => {
              this.montarMsgAlerta(erro.error.detail);
            });
    }
  
    cancelar(): void {
      window.history.back();
    }
  
    verificaIdRota(){
      this.route.params.subscribe((params) => {
        const id = params['id'];
        if (id) {
          this.titulo = 'Edição de Estabelecimento';
          this.obterPorId(id);
        }
        else{
          this.titulo = 'Cadastro de Estabelecimento'
        }
      });
    }
  
      obterPorId(id) {
        this.blockUI.start();
        this.estabelecimentoService.buscarPorId(id)
          .pipe(finalize(() => this.blockUI.stop()))
          .subscribe((response) => {
            this.preencherModel(response);
          });
      }
  
      private preencherModel(response) {
        this.estabelecimento = response;
      }
    

}
