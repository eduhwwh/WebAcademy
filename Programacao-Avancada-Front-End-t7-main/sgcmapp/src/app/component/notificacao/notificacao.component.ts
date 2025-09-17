import { Component, inject } from '@angular/core';
import { NotificacaoService } from '../../service/notificacao.service';
import { NgbToast } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-notificacao',
  imports: [NgbToast, CommonModule],
  templateUrl: './notificacao.component.html',
  host: {
    class: 'toast-container position-fixed top-0 end-0 m-3',
    style: 'z-index: 1200'
  }
})
export class NotificacaoComponent {
  servico = inject(NotificacaoService);

  fechar(indice: number): void{
    this.servico.remover(indice);

  }

}
