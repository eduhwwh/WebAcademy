import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Especialidade } from '../../../model/especialidade';
import { Profissional } from '../../../model/profissional';
import { Unidade } from '../../../model/unidade';
import { EspecialidadeService } from '../../../service/especialidade.service';
import { ProfissionalService } from '../../../service/profissional.service';
import { UnidadeService } from '../../../service/unidade.service';
import { ICrudForm } from '../../i-crud-form';
import { CommonModule } from '@angular/common';
import { notBlankValidator } from '../../../validator/not-blank.validator';
import { NotificacaoService } from '../../../service/notificacao.service';

@Component({
  selector: 'app-profissional-form',
  imports: [FormsModule, RouterLink, ReactiveFormsModule, CommonModule],
  templateUrl: './profissional-form.component.html',
  styles: ``
})
export class ProfissionalFormComponent implements ICrudForm<Profissional>, OnInit {

  private servico = inject(ProfissionalService);
  private servicoEspecialidade = inject(EspecialidadeService);
  private servicoUnidade = inject(UnidadeService);
  private roteador = inject(Router);
  private rota = inject(ActivatedRoute);
  private notificacao = inject(NotificacaoService);
  private id = this.rota.snapshot.queryParamMap.get('id');

  ngOnInit(): void {
    this.servicoEspecialidade.consultar().subscribe({
      next: resposta => this.especialidades = resposta
    });
    
    this.servicoUnidade.consultar().subscribe({
      next: resposta => this.unidades = resposta
    });

    if (this.id) {
      this.servico.consultarPorId(+this.id).subscribe({
        next: resposta => {
          this.registro = resposta;
          this.formProfissional.patchValue(this.registro);
        }
      });
    }
  }

  registro: Profissional = <Profissional>{};
  especialidades: Especialidade[] = [];
  unidades: Unidade[] = [];

  formProfissional = new FormGroup({
    nome: new FormControl<string | null>(null, notBlankValidator(5)),
    registroConselho: new FormControl<string | null>(null),
    especialidade_id: new FormControl<number | null>(null),
    unidade_id: new FormControl<number | null>(null),
    telefone: new FormControl<string | null>(null, Validators.pattern('^\\(\\d{2}\\) \\d{4,5}-\\d{4}$')),
    email: new FormControl<string | null>(null, Validators.email)
  });

  get form() {
    return this.formProfissional.controls;
  }
  
  salvar(): void {
    this.registro = Object.assign(this.registro, this.formProfissional.value);
    this.servico.salvar(this.registro).subscribe({
      next: id => {
        if (!this.id) {
          this.notificacao.enviarNotificacaoInfo(`ID gerado: ${id}`);
        }
      },
      error: () => this.notificacao.enviarNotificacaoErro('Falha na operação.'),
      complete: () => {
        this.notificacao.enviarNotificacaoSucesso('Operação realizada com sucesso.');
        this.roteador.navigate(['/profissional-list']);
      }
    });
  }

}
