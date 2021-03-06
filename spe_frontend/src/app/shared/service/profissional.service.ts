import { Injectable } from '@angular/core';
import { Profissional } from 'src/app/domain/profissional';
import { environment } from 'src/environments/environment';
import { Table } from 'primeng';
import { Observable } from 'rxjs';
import { Page } from 'src/app/domain/page';
import { HttpClient } from '@angular/common/http';
import { RequestUtil } from 'src/app/util/request-util';


@Injectable()
export class ProfissionalService {

  resourceUrl = environment.apiAcessoService + '/profissionais';

  constructor(private httpClient: HttpClient) { }

  listar(profissional: Profissional, datatable: Table): Observable<Page<Profissional>> {
    const vrr = { params: RequestUtil.getRequestParamsTable(datatable) };
    return this.httpClient.post<Page<Profissional>>(this.resourceUrl + '/listar', profissional, vrr);
  }

  salvar(profissional: Profissional): Observable<Profissional> {
      return this.httpClient.post<Profissional> (this.resourceUrl, profissional);
  }

  editar(profissional: Profissional): Observable<Profissional> {
      return this.httpClient.put<Profissional> (this.resourceUrl, profissional);
  }

  excluir(id: number): Observable<void> {
      return this.httpClient.delete<void>(this.resourceUrl + `/${id}`);
  }

  buscarPorId(id: number): Observable<Profissional> {
      return this.httpClient.get<Profissional> (this.resourceUrl + `/${id}`);
  }
}
