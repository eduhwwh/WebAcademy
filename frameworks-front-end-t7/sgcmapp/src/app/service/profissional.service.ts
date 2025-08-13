import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ICrudService } from './i-crud-service';
import { Profissional } from '../model/profissional';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService implements ICrudService<Profissional> {
  
  private http = inject(HttpClient);
  apiUrl: string = `${environment.API_URL}/profissional`;

  consultar(termoBusca?: string): Observable<Profissional[]> {
    let url = `${this.apiUrl}/consultar`;
    let parametros = new HttpParams();

    if (termoBusca) {
      parametros = parametros.set('termoBusca', termoBusca);
    }

    return this.http.get<Profissional[]>(url, {
      params: parametros
    });
  }

  consultarPorId(id: number): Observable<Profissional> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Profissional>(url);
  }

  salvar(objeto: Profissional): Observable<number | void> {
    if (objeto.id) {
      // PUT para atualizar
      return this.http.put<void>(`${this.apiUrl}/${objeto.id}`, objeto);
    } else {
      // POST para criar
      return this.http.post<number>(this.apiUrl, objeto);
    }
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
