import { Component } from '@angular/core';
import { Atendimento } from '../../../model/atendimento';
import { ICrudList } from '../../i-crud-list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-agenda-list',
  imports: [CommonModule],
  templateUrl: './agenda-list.component.html',
  styles: ``
})
export class AgendaListComponent implements ICrudList<Atendimento> {

  registros: Atendimento[] = [];

  consultar(termoBusca?: string): void {
    throw new Error('Method not implemented.');
  }

  remover(id: number): void {
    console.log('Remover: ', id);
  }

}
