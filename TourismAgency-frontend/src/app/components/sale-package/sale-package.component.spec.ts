import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalePackageComponent } from './sale-package.component';

describe('SalePackageComponent', () => {
  let component: SalePackageComponent;
  let fixture: ComponentFixture<SalePackageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SalePackageComponent]
    });
    fixture = TestBed.createComponent(SalePackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
