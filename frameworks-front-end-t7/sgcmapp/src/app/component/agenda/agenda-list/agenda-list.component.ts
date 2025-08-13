import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Atendimento } from '../../../model/atendimento';
import { ICrudList } from '../../i-crud-list';
import { BarraComandosComponent } from "../../barra-comandos/barra-comandos.component";
import { AtendimentoService } from '../../../service/atendimento.service';

@Component({
  selector: 'app-agenda-list',
  imports: [CommonModule, BarraComandosComponent],
  templateUrl: './agenda-list.component.html',
  styles: ``
})
export class AgendaListComponent implements ICrudList<Atendimento>, OnInit {

  private servico = inject(AtendimentoService);

  ngOnInit(): void {
    this.consultar();
  }

  registros: Atendimento[] = [];

  consultar(termoBusca?: string): void {
    this.servico.consultar(termoBusca).subscribe({
      next: resposta => this.registros = resposta
    });
  }

  remover(id: number): void {
    if (confirm('Confirma cancelamento do agendamento?')){
      this.servico.remover(id).subscribe({
        complete: () => this.consultar()
      });
    }
  }
  
  atualizarStatus(id: number): void {
    if (confirm('Confirma alteração no status do agendamento?')) {
      this.servico.atualizarStatus(id).subscribe({
        next: status => alert(`Status alterado para ${status}`),
        complete: () => this.consultar()
      })
    }
  }

}
