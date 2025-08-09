import { Component, OnInit } from '@angular/core';
import { Atendimento } from '../../../model/atendimento';
import { ICrudList } from '../../i-crud-list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-agenda-list',
  imports: [CommonModule],
  templateUrl: './agenda-list.component.html',
  styles: ``
})
export class AgendaListComponent implements ICrudList<Atendimento>, OnInit {

  ngOnInit(): void {
    this.consultar()
  }

  registros: Atendimento[] = [];

  consultar(termoBusca?: string): void {
    fetch('./json/agendamentos.json')
    .then(resposta => resposta.json())
    .then(dados => this.registros = dados)
  }

  remover(id: number): void {
    console.log('Remover: ', id);
  }

}
