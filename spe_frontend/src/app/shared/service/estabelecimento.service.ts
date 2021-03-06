import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Table } from 'primeng';
import { Observable } from 'rxjs';
import { Page } from 'src/app/domain/page';
import { HttpClient } from '@angular/common/http';
import { RequestUtil } from 'src/app/util/request-util';
import { Estabelecimento } from 'src/app/domain/estabelecimento';


@Injectable()
export class EstabelecimentoService {

  resourceUrl = environment.apiAcessoService + '/estabelecimentos';

  constructor(private httpClient: HttpClient) { }

  listar(estabelecimento: Estabelecimento, datatable: Table): Observable<Page<Estabelecimento>> {
    const vrr = { params: RequestUtil.getRequestParamsTable(datatable) };
    return this.httpClient.post<Page<Estabelecimento>>(this.resourceUrl + '/listar', estabelecimento, vrr);
  }

  salvar(estabelecimento: Estabelecimento): Observable<Estabelecimento> {
      return this.httpClient.post<Estabelecimento> (this.resourceUrl, estabelecimento);
  }

  editar(estabelecimento: Estabelecimento): Observable<Estabelecimento> {
      return this.httpClient.put<Estabelecimento> (this.resourceUrl, estabelecimento);
  }

  excluir(id: number): Observable<void> {
      return this.httpClient.delete<void>(this.resourceUrl + `/${id}`);
  }

  buscarPorId(id: number): Observable<Estabelecimento> {
      return this.httpClient.get<Estabelecimento> (this.resourceUrl + `/${id}`);
  }
}

