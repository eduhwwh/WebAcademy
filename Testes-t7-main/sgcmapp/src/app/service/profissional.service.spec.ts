import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { ProfissionalService } from './profissional.service';

describe('ProfissionalService', () => {
  let service: ProfissionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(ProfissionalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
