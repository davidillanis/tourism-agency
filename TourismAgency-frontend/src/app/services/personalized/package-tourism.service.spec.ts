import { TestBed } from '@angular/core/testing';

import { PackageTourismService } from './package-tourism.service';

describe('PackageTourismService', () => {
  let service: PackageTourismService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PackageTourismService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
