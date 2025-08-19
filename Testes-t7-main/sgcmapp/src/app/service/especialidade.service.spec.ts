import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { EspecialidadeService } from './especialidade.service';

describe('EspecialidadeService', () => {
  let service: EspecialidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(EspecialidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
