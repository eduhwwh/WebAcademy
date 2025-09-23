import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

const ERRO_HTTP: Record<number, string> = {
  401: 'Acesso não autorizado',
  403: 'Suas crendencias não permitem acesso ao recurso',
  404: 'Recurso não encontrado.',
  500: 'Erro interno do servidor.'
}

export const erroInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError(erro => {
      let mensagemErro = ERRO_HTTP[erro.status] || erro.error?.message || 'Falha na requisição';
      alert(mensagemErro);
      return throwError(() => erro);
    })
  );
};
