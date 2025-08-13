import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Profissional } from '../model/profissional';
import { ICrudService } from './i-crud-service';

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

    return this.http.get<Profissional[]>(url, { params: parametros });
  }

  consultarPorId(id: number): Observable<Profissional> {
    throw new Error('Method not implemented.');
  }

  salvar(objeto: Profissional): Observable<number | void> {
    throw new Error('Method not implemented.');
  }

  remover(id: number): Observable<void> {
    throw new Error('Method not implemented.');
  }

}
