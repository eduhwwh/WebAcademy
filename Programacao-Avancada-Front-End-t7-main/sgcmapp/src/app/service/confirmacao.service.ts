import { inject, Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalConfirmacaoComponent } from '../component/modal-confirmacao/modal-confirmacao.component';

@Injectable({
  providedIn: 'root'
})
export class ConfirmacaoService {
  private modal = inject(NgbModal);

  async confirmar(mensagem?: string): Promise<boolean> {
    const modalRef = this.modal.open(ModalConfirmacaoComponent);
    modalRef.componentInstance.mensagem = mensagem;

    return modalRef.result.then(() => true).catch(() => false);
  }
}
