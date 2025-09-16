import { Component, inject } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-confirmacao',
  imports: [],
  templateUrl: './modal-confirmacao.component.html',
  styles: ``
})
export class ModalConfirmacaoComponent {
  activeModal = inject(NgbActiveModal);

  titulo = 'Confirmação';
  mensagem = 'Deseja continuar?';

  confirmar(): void {
    this.activeModal.close(true);
  }

  cancelar(): void {
    this.activeModal.dismiss(false);
  }
}
