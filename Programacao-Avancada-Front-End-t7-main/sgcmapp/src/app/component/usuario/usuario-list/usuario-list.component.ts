import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '../../../model/usuario';
import { UsuarioService } from '../../../service/usuario.service';
import { BarraComandosComponent } from '../../barra-comandos/barra-comandos.component';
import { ICrudList } from '../../i-crud-list';
import { ConfirmacaoService } from '../../../service/confirmacao.service';
import { NotificacaoService } from '../../../service/notificacao.service';
import { RespostaPaginada } from '../../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../../model/requisicao-paginada';
import { OrdenacaoDirective } from '../../../directive/ordenacao.directive';
import { SeletorTamanhoPaginaComponent } from '../../seletor-tamanho-pagina/seletor-tamanho-pagina.component';

@Component({
  selector: 'app-usuario-list',
  imports: [CommonModule, BarraComandosComponent, RouterLink, NgbTooltipModule, NgbPaginationModule, OrdenacaoDirective, SeletorTamanhoPaginaComponent],
  templateUrl: './usuario-list.component.html',
  styles: ``
})
export class UsuarioListComponent implements ICrudList<Usuario>, OnInit {

  private servico = inject(UsuarioService);
  private confirmacao = inject(ConfirmacaoService);
  private servicoNotificacao = inject(NotificacaoService);
  private termoBusca: string | undefined = '';

  ngOnInit(): void {
    this.consultar();
  }

  registros: Usuario[] = [];
  respostaPaginada: RespostaPaginada<Usuario> = <RespostaPaginada<Usuario>>{};
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  itensPorPagina = [5, 10, 20, 50];

  mudarPagina(pagina: number): void {
    this.requisicaoPaginada.page = pagina - 1;
    this.consultar(this.termoBusca);
  }

  ordenar(ordenacao: string[]): void {
    this.requisicaoPaginada.sort = ordenacao;
    this.requisicaoPaginada.page = 0;
    this.consultar(this.termoBusca);
  }

  alterarTamanhoPagina(novoTamanho: number): void {
    this.requisicaoPaginada.size = novoTamanho;
    this.requisicaoPaginada.page = 0;
    this.consultar(this.termoBusca);
  }

  consultar(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.consultarPaginado(termoBusca, this.requisicaoPaginada).subscribe({
      next: resposta => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }

  async remover(id: number): Promise<void> {
    const confirmado = await this.confirmacao.confirmar('Confirma a exclusão do usuário?');
    if (confirmado) {
      this.servico.remover(id).subscribe({
        complete: () => {
          this.servicoNotificacao.enviarNotificacaoSucesso();
          this.consultar();
        }
      });
    }
  }

}
