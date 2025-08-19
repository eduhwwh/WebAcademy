import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { UnidadeService } from './unidade.service';

describe('UnidadeService', () => {
  let service: UnidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(UnidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
