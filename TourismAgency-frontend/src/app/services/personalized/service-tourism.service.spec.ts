import { TestBed } from '@angular/core/testing';

import { ServiceTourismService } from './service-tourism.service';

describe('ServiceTourismService', () => {
  let service: ServiceTourismService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceTourismService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
