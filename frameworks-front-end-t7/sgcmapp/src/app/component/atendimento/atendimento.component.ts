import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { BarraComandosComponent } from "../barra-comandos/barra-comandos.component";
import { ICrudList } from '../i-crud-list';
import { Atendimento } from '../../model/atendimento';
import { AtendimentoService } from '../../service/atendimento.service';

@Component({
  selector: 'app-atendimento',
  imports: [CommonModule, BarraComandosComponent],
  templateUrl: './atendimento.component.html',
  styles: ``
})
export class AtendimentoComponent implements ICrudList<Atendimento> {
  
  private servico = inject(AtendimentoService);

  ngOnInit(): void {
    this.consultar();
  }

  registros: Atendimento[] = [];

  consultar(termoBusca?: string): void {
    const status = ['CHEGADA', 'ATENDIMENTO']
    this.servico.consultar(termoBusca,status).subscribe({
      next: resposta => this.registros = resposta
    });
  }

  remover(id: number): void {
    throw new Error('Method not implemented.');
  }

}
