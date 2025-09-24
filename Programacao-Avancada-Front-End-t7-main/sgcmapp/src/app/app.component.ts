import { Component, effect, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NotificacaoComponent } from './component/notificacao/notificacao.component';
import { Usuario } from './model/usuario';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, NotificacaoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  private servicoLogin: LoginService = inject(LoginService);
  usuario: Usuario = <Usuario>{};
  title = 'SGCM';

  constructor() {
    effect(() => {
      this.usuario = this.servicoLogin.usuarioAutenticado();
    });
  }

  logout(): void {
    this.servicoLogin.logout();
  }

  isUsuarioAutenticado(): boolean {
    return this.servicoLogin.isUsuarioAutenticado();
  }

  isAdmin(): boolean {
    return this.usuario.papel == 'ROLE_ADMIN';
  }
}
