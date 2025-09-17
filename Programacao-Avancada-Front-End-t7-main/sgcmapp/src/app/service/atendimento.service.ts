 import { inject, Injectable } from '@angular/core';
import { ICrudService } from './i-crud-service';
import { Atendimento } from '../model/atendimento';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { IPageService } from './i-page-service';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';

@Injectable({
  providedIn: 'root'
})
export class AtendimentoService implements ICrudService<Atendimento>, IPageService<Atendimento> {


  private http = inject(HttpClient);

  apiUrl: string = `${environment.API_URL}/atendimento`;

  consultar(termoBusca?: string, status?: string[]): Observable<Atendimento[]> {
    let url = `${this.apiUrl}/consultar`;
    let parametros = new HttpParams();

    if (termoBusca) {
      parametros = parametros.set('termoBusca', termoBusca);
    }
    if (status) {
      parametros = parametros.set('status', status.join(','));
    }

    return this.http.get<Atendimento[]>(url, { params: parametros });
  }
  consutarPaginado(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Atendimento>> {
    let url = `${this.apiUrl}/consultar`;
    let parametros = new HttpParams();

    if (termoBusca) {
      parametros = parametros.set('termoBusca', termoBusca);
    }
    if (status) {
      parametros = parametros.set('status', status.join(','));
    }

    return this.http.get<Atendimento[]>(url, { params: parametros });
  }

  consultarPorId(id: number): Observable<Atendimento> {
    let url = `${this.apiUrl}/consultar/${id}`;
    return this.http.get<Atendimento>(url);
  }

  salvar(objeto: Atendimento): Observable<number | void> {
    let url = this.apiUrl;
    if (objeto.id) {
      url += '/atualizar';
      return this.http.put<void>(url, objeto);
    } else {
      url += '/inserir';
      return this.http.post<number>(url, objeto);
    }
  }

  remover(id: number): Observable<void> {
    let url = `${this.apiUrl}/remover/${id}`;
    return this.http.delete<void>(url);
  }

  atualizarStatus(id: number): Observable<string> {
    let url = `${this.apiUrl}/status/${id}`;
    return this.http.put<string>(url, null);
  }

}
