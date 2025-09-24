import { HttpClient, HttpRequest } from '@angular/common/http';
import { inject, Injectable, signal, WritableSignal } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private http: HttpClient = inject(HttpClient);
  private roteador: Router = inject(Router);
  usuarioAutenticado: WritableSignal<Usuario> = signal<Usuario>(<Usuario>{});

  constructor() {
    const dadosUsuario = sessionStorage.getItem('usuario') || '{}';
    const usuario = JSON.parse(dadosUsuario);
    this.usuarioAutenticado.set(usuario);
  }

  private iniciarSessaoUsuario(token: string): void {
    const dados = token.split('.')[1];
    const dadosDecodificados = atob(dados);
    const conteudo = JSON.parse(dadosDecodificados);

    const expiracao = conteudo.exp * 1000;

    const usuario = <Usuario>{};
    usuario.nomeUsuario = conteudo.sub;
    usuario.nomeCompleto = conteudo.nomeCompleto;
    usuario.papel = conteudo.papel;

    sessionStorage.setItem('token', token);
    sessionStorage.setItem('usuario', JSON.stringify(usuario));
    sessionStorage.setItem('tokenExp', expiracao.toString());

    this.usuarioAutenticado.set(usuario);
  }

  login(usuario: Usuario): void {
    const url = `${environment.API_URL}/login/autenticar`;
    this.http.post(url, usuario, {responseType: 'text'}).subscribe({
      next: (token: string) => {
        this.iniciarSessaoUsuario(token);
      },
      complete: () => {
        this.roteador.navigate(['/']);
      }
    });
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('tokenExp');
    this.roteador.navigate(['/login']);
  }

  isUsuarioAutenticado(): boolean {
    const token = sessionStorage.getItem('token');
    if (token == null) {
      return false;
    }

    const expiracao = sessionStorage.getItem('tokenExp');
    const dataExpiracao = new Date(Number(expiracao));
    const agora = new Date();
    const isTokenExpirado = dataExpiracao < agora;
    if (isTokenExpirado) {
      this.logout();
    }

    return !isTokenExpirado;
  }

  alterarCabecalho(req: HttpRequest<any>): HttpRequest<any> {
    const token = sessionStorage.getItem('token');
    if (token) {
      return req.clone({
        setHeaders: {
          'Authorization': `Bearer ${token}`
        }
      });
    }
    return req;
  }
}
