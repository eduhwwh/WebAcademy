import { Injectable, signal } from '@angular/core';
import { Notificacao } from '../model/notificacao';
import { ETipoNotificacao } from '../model/e-tipo-notificacao';

@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {
  notificacoes = signal<Notificacao[]>([]);

  enviar(notificacao: Notificacao): void {
    this.notificacoes.update(notificacoes => [...notificacoes, notificacao]);
  }

  remover(indice: number): void {
    this.notificacoes.update(
      notificacoes => notificacoes.filter(
        (_, i) => i !== indice));
  }

  enviarNotificacaoSucesso(mensagem?: string): void {
    mensagem = mensagem || 'Operação realizada com sucesso!';
    const notificacao: Notificacao = {
      tipo: ETipoNotificacao.SUCESSO,
      mensagem: mensagem
    }
    this.enviar(notificacao);
  }

  enviarNotificacaoErro(mensagem?: string): void {
    mensagem = mensagem || 'Ocorreu um erro durante a operação!';
    const notificacao: Notificacao = {
      tipo: ETipoNotificacao.ERRO,
      mensagem: mensagem
    }
    this.enviar(notificacao);
  }

  enviarNotificacaoInfo(mensagem: string): void {
    const notificacao: Notificacao = {
      tipo: ETipoNotificacao.INFO,
      mensagem: mensagem
    }
    this.enviar(notificacao);
  }
}
