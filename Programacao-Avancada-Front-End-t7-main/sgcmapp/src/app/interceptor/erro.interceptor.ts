import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { LoginService } from '../service/login.service';
import { NotificacaoService } from '../service/notificacao.service';

const ERRO_HTTP: Record<number, string> = {
  401: 'Acesso não autorizado.',
  403: 'Suas credenciais não permitem acesso ao recurso.',
  404: 'Recurso não encontrado.',
  500: 'Erro interno do servidor.'
}

export const erroInterceptor: HttpInterceptorFn = (req, next) => {
  const servicoNotificacao = inject(NotificacaoService);
  const servicoLogin = inject(LoginService);
  return next(req).pipe(
    catchError(erro => {
      let mensagemErro = ERRO_HTTP[erro.status] || erro.error?.message || 'Falha na requisição';
      if (erro.status == 403) {
        servicoLogin.logout();
      }
      servicoNotificacao.enviarNotificacaoErro(mensagemErro);
      return throwError(() => erro);
    })
  );
};
