import { Component, inject } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Usuario } from '../../model/usuario';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  private servico: LoginService = inject(LoginService);
  usuario: Usuario = <Usuario>{};

  login(): void{
    this.servico.login(this.usuario);

  }

}
