import { Observable } from "rxjs";
import { RequisicaoPaginada } from "../model/requisicao-paginada";
import { RespostaPaginada } from "../model/resposta-paginada";

export interface IPageService<T> {
    consutarPaginado(termoBusca?: string, paginacao?: RequisicaoPaginada)
    : Observable<RespostaPaginada<T>>;
}
