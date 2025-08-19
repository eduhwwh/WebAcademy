import { TestBed } from '@angular/core/testing';
import { AtendimentoService } from './atendimento.service';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';

describe('AtendimentoService', () => {
  let service: AtendimentoService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()]
    });
    service = TestBed.inject(AtendimentoService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('deve chamar HttpClient ao executar o método consultar()', () => {
    service.consultar().subscribe();
    const endpoint = '/atendimento/consultar';
    const testeHttp = httpTestingController.expectOne(req => {
      let url = new URL(req.url);
      return url.pathname == endpoint;
    });
    expect(testeHttp.request.params.get('termoBusca')).toBeNull();
    expect(testeHttp.request.method).toBe('GET');
  });
});
