import { ComponentFixture, TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { routes } from '../../../app.routes';
import { AgendaFormComponent } from './agenda-form.component';
import mockConvenios from '../../../../assets/json/convenios.json';
import { of } from 'rxjs';
import { ConvenioService } from '../../../service/convenio.service';

describe('AgendaFormComponent', () => {
  let component: AgendaFormComponent;
  let fixture: ComponentFixture<AgendaFormComponent>;

  const servicoConvenio = {
    consultarAtivos: jasmine.createSpy('consultarAtivos')
      .and.returnValue(of(mockConvenios))
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        AgendaFormComponent,
        RouterModule.forRoot(routes)],
      providers: [
        provideHttpClient(),
        { provide: ConvenioService, useValue: servicoConvenio }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('deve listar as opções de convênio no formulário ao iniciar', () => {
    component.ngOnInit();
    fixture.detectChanges();
    const select = fixture.nativeElement.querySelector('select#convenio');
    expect(select).not.toBeNull();
    mockConvenios.forEach(
      convenio => expect(select.innerHTML).toContain(convenio.nome)
    );
  });
});
