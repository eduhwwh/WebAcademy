import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Convenio } from '../model/convenio';
import { ICrudService } from './i-crud-service';

@Injectable({
  providedIn: 'root'
})
export class ConvenioService implements ICrudService<Convenio> {
  
  private http = inject(HttpClient);

  apiUrl: string = `${environment.API_URL}/convenio`;

  consultar(termoBusca?: string): Observable<Convenio[]> {
    throw new Error('Method not implemented.');
  }

  consultarPorId(id: number): Observable<Convenio> {
    throw new Error('Method not implemented.');
  }

  salvar(objeto: Convenio): Observable<number | void> {
    throw new Error('Method not implemented.');
  }

  remover(id: number): Observable<void> {
    throw new Error('Method not implemented.');
  }

  consultarAtivos(): Observable<Convenio[]> {
    let url = `${this.apiUrl}/ativos`;
    return this.http.get<Convenio[]>(url);
  }

}
