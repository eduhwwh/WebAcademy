import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Atendimento } from '../../../model/atendimento';
import { Convenio } from '../../../model/convenio';
import { Paciente } from '../../../model/paciente';
import { Profissional } from '../../../model/profissional';
import { AtendimentoService } from '../../../service/atendimento.service';
import { ConvenioService } from '../../../service/convenio.service';
import { PacienteService } from '../../../service/paciente.service';
import { ProfissionalService } from '../../../service/profissional.service';
import { diaUtilValidator } from '../../../validator/dia-util.validator';
import { ICrudForm } from '../../i-crud-form';
import { NotificacaoService } from '../../../service/notificacao.service';

@Component({
  selector: 'app-agenda-form',
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './agenda-form.component.html',
  styles: ``
})
export class AgendaFormComponent implements ICrudForm<Atendimento>, OnInit {

  private servico = inject(AtendimentoService);
  private servicoConvenio = inject(ConvenioService);
  private servicoPaciente = inject(PacienteService);
  private servicoProfissional = inject(ProfissionalService);
  private roteador = inject(Router);
  private rota = inject(ActivatedRoute);
  private notificacao = inject(NotificacaoService);
  private id = this.rota.snapshot.queryParamMap.get('id');

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

    if (this.id) {
      this.servico.consultarPorId(+this.id).subscribe({
        next: resposta => {
          this.registro = resposta;
          this.formAgenda.patchValue(this.registro);
        }
      });
    }
  }

  registro: Atendimento = <Atendimento>{};
  convenios: Convenio[] = [];
  pacientes: Paciente[] = [];
  profissionais: Profissional[] = [];

  formAgenda = new FormGroup({
    data: new FormControl<string | null>(null, [Validators.required, diaUtilValidator()]),
    hora: new FormControl<string | null>(null, Validators.required),
    profissional_id: new FormControl<number | null>(null, Validators.required),
    paciente_id: new FormControl<number | null>(null, Validators.required),
    convenio_id: new FormControl<number | null>(null),
  });

  get form() {
    return this.formAgenda.controls;
  }

  get dataMinima(): string {
    return new Date().toISOString().split('T')[0];
  }

  salvar(): void {
    Object.assign(this.registro, this.formAgenda.value);
    this.servico.salvar(this.registro).subscribe({
      next: id => {
        if (!this.id) {
          this.notificacao.enviarNotificacaoInfo(`ID gerado: ${id}`);
        }
      },
      error: () => this.notificacao.enviarNotificacaoErro('Falha na operação.'),
      complete: () => {
        this.notificacao.enviarNotificacaoSucesso('Operação realizada com sucesso.');
        this.roteador.navigate(['/agenda-list']);
      }
    });
  }

}
