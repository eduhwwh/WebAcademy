import { inject, Injectable } from '@angular/core';
import { Usuario } from '../model/usuario';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private http: HttpClient = inject(HttpClient);
  private roteador: Router = inject(Router);

  constructor() { }

  private iniciarSessaoUsuario(token: string): void {
    const dados = token.split('.')[1];
    const dadosDecodificado = atob(dados);
    const conteudo = JSON.parse(dadosDecodificado);

    const expericao = conteudo.exp * 1000;

    const usuario = <Usuario>{};

    usuario.nomeUsuario = conteudo.sub;
    usuario.nomeCompleto = conteudo.nomeCompleto;
    usuario.papel = conteudo.papel;

    sessionStorage.setItem('token', token);
    sessionStorage.setItem('usuario', JSON.stringify(usuario));
    sessionStorage.setItem('tokenExp', expericao.toString());
  }

  login(usuario: Usuario): void {
    const url = `${environment.API_URL}/login/autenticar`;
    this.http.post(url, usuario, { responseType: 'text' }).subscribe({
      next: (token: string) => {
        this.iniciarSessaoUsuario(token);
      },
      complete: () => {
        this.roteador.navigate(['/']);
      },

    });
  }

  alterarCabecalho(req: HttpRequest<any>): HttpRequest<any> {

    const token = sessionStorage.getItem('token');

    return req.clone({
      setHeaders: {
        'Authorization': `Beare ${token}`
      }
    });
  }

}
