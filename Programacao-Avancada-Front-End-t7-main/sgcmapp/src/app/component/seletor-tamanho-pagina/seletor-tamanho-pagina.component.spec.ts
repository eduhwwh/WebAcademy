import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeletorTamanhoPaginaComponent } from './seletor-tamanho-pagina.component';

describe('SeletorTamanhoPaginaComponent', () => {
  let component: SeletorTamanhoPaginaComponent;
  let fixture: ComponentFixture<SeletorTamanhoPaginaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeletorTamanhoPaginaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeletorTamanhoPaginaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
