import { inject, Injectable } from '@angular/core';
import { ICrudService } from './i-crud-service';
import { Paciente } from '../model/paciente';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PacienteService implements ICrudService<Paciente> {

  private http = inject(HttpClient)

  apiUrl: string = `${environment.API_URL}/paciente`

  consultar(termoBusca?: string): Observable<Paciente[]> {
    let url = `${this.apiUrl}/consultar`;
    let parametros = new HttpParams();

    if(termoBusca){
      parametros = parametros.set('termoBusca', termoBusca);
    }

    return this.http.get<Paciente[]>(url, {
      params: parametros
    });
  }
  consultarPorId(id: number): Observable<Paciente> {
    throw new Error('Method not implemented.');
  }
  salvar(objeto: Paciente): Observable<number | void> {
    throw new Error('Method not implemented.');
  }
  remover(id: number): Observable<void> {
    throw new Error('Method not implemented.');
  }



}
