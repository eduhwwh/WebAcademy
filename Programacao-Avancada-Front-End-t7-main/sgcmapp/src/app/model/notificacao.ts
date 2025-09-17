import { ETipoNotificacao } from "./e-tipo-notificacao"

export type Notificacao = {
    tipo: ETipoNotificacao;
    mensagem: string;
}
