import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Atendimento } from '../../../model/atendimento';
import { ICrudForm } from '../../i-crud-form';

@Component({
  selector: 'app-agenda-form',
  imports: [FormsModule],
  templateUrl: './agenda-form.component.html',
  styles: ``
})
export class AgendaFormComponent implements ICrudForm<Atendimento> {

  registro: Atendimento = <Atendimento>{};

  salvar(): void {
    throw new Error('Method not implemented.');
  }

}
