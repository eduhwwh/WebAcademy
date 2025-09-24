import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Usuario } from '../../../model/usuario';
import { UsuarioService } from '../../../service/usuario.service';
import { ICrudForm } from '../../i-crud-form';
import { NotificacaoService } from '../../../service/notificacao.service';

@Component({
  selector: 'app-usuario-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './usuario-form.component.html',
  styles: ``
})
export class UsuarioFormComponent implements ICrudForm<Usuario>, OnInit {

  private servico = inject(UsuarioService);
  private roteador = inject(Router);
  private rota = inject(ActivatedRoute);
  private notificacao = inject(NotificacaoService);
  private id = this.rota.snapshot.queryParamMap.get('id');

  ngOnInit(): void {
    if (this.id) {
      this.servico.consultarPorId(+this.id).subscribe({
        next: resposta => this.registro = resposta
      });
    }
  }

  registro: Usuario = <Usuario>{};
  
  salvar(): void {
    this.servico.salvar(this.registro).subscribe({
      next: id => {
        if (!this.id) {
          this.notificacao.enviarNotificacaoInfo(`ID gerado: ${id}`);
        }
      },
      error: () => this.notificacao.enviarNotificacaoErro('Falha na operação.'),
      complete: () => {
        this.notificacao.enviarNotificacaoSucesso('Operação realizada com sucesso.');
        this.roteador.navigate(['/config/usuario-list']);
      }
    });
  }

}
