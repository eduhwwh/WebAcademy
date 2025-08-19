import { TestBed } from '@angular/core/testing';

import { provideHttpClient } from '@angular/common/http';
import { ConvenioService } from './convenio.service';

describe('ConvenioService', () => {
  let service: ConvenioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(ConvenioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
