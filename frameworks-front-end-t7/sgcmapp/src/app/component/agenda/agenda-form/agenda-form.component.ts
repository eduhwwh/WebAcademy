import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Atendimento } from '../../../model/atendimento';
import { ICrudForm } from '../../i-crud-form';
import { Convenio } from '../../../model/convenio';
import { Profissional } from '../../../model/profissional';
import { AtendimentoService } from '../../../service/atendimento.service';
import { ConvenioService } from '../../../service/convenio.service';
import { PacienteService } from '../../../service/paciente.service';
import { ProfissionalService } from '../../../service/profissional.service';
import { Paciente } from '../../../model/paciente';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-agenda-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './agenda-form.component.html',
  styles: ``
})
export class AgendaFormComponent implements ICrudForm<Atendimento>, OnInit {

  private servico = inject(AtendimentoService);
  private servicoConvenio = inject(ConvenioService);
  private servicoPaciente = inject(PacienteService);
  private servicoProfissional = inject(ProfissionalService);

  ngOnInit(): void {
    this.servicoConvenio.consultarAtivos().subscribe({
      next: resposta => this.convenios = resposta
    });


    this.servicoPaciente.consultar().subscribe({
      next: resposta => this.pacientes = resposta
    });

    this.servicoProfissional.consultar().subscribe({
      next: resposta => this.profissionais = resposta
    });
  }

  registro: Atendimento = <Atendimento>{};
  convenios: Convenio[] = [];
  pacientes: Paciente[] = [];
  profissionais: Profissional[] = [];

  salvar(): void {
    throw new Error('Method not implemented.');
  }

}
