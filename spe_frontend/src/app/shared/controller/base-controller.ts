import { MessageService } from 'primeng';

export abstract class BaseController {

    constructor(private messageService: MessageService) { }

    montarMsgAlerta(texto: string) {
        this.messageService.add({ severity: 'error', summary: 'Alerta', detail: texto });
    }

    montarMsgSucesso(texto: string) {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: texto });
    }

    montarMsgAviso(texto: string) {
        this.messageService.add({ severity: 'warn', summary: 'Aviso', detail: texto });
    }

}
