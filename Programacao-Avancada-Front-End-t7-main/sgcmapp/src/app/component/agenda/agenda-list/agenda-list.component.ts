import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Atendimento } from '../../../model/atendimento';
import { AtendimentoService } from '../../../service/atendimento.service';
import { BarraComandosComponent } from "../../barra-comandos/barra-comandos.component";
import { ICrudList } from '../../i-crud-list';
import { NgbPaginationModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmacaoService } from '../../../service/confirmacao.service';
import { NotificacaoService } from '../../../service/notificacao.service';
import { RespostaPaginada } from '../../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../../model/requisicao-paginada';
import { OrdenacaoDirective } from '../../../directive/ordenacao.directive';
import { SeletorTamanhoPaginaComponent } from '../../seletor-tamanho-pagina/seletor-tamanho-pagina.component';

@Component({
  selector: 'app-agenda-list',
  imports: [CommonModule, BarraComandosComponent, RouterLink, NgbTooltipModule, NgbPaginationModule, OrdenacaoDirective, SeletorTamanhoPaginaComponent],
  templateUrl: './agenda-list.component.html',
  styles: ``
})
export class AgendaListComponent implements ICrudList<Atendimento>, OnInit {

  private servico = inject(AtendimentoService);
  private confirmacao = inject(ConfirmacaoService);
  private servicoNotificacao = inject(NotificacaoService);
  private termoBusca: string | undefined = ''; 

  ngOnInit(): void {
    this.consultar();
  }

  registros: Atendimento[] = [];
  respostaPaginada: RespostaPaginada<Atendimento> = <RespostaPaginada<Atendimento>>{};
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
    const status = ['AGENDADO', 'CONFIRMADO']
    this.servico.consultarPaginado(termoBusca, this.requisicaoPaginada, status).subscribe({
      next: resposta => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }

  async remover(id: number): Promise<void> {
    const confirmado = await this.confirmacao.confirmar('Confirma cancelamento do agendamento?');
    if (confirmado) {
      this.servico.remover(id).subscribe({
        complete: () => {
          this.servicoNotificacao.enviarNotificacaoSucesso();
          this.consultar();
        }
      });
    }
  }
  
  async atualizarStatus(id: number): Promise<void> {
    const confirmado = await this.confirmacao.confirmar(
      'Confirma alteração no status do agendamento?'
    );
    if (confirmado) {
      this.servico.atualizarStatus(id).subscribe({
        next: status => this.servicoNotificacao.enviarNotificacaoInfo(`Status alterado para ${status}`),
        complete: () => {
          this.servicoNotificacao.enviarNotificacaoSucesso();
          this.consultar();
        }
      })
    }
  }

}
