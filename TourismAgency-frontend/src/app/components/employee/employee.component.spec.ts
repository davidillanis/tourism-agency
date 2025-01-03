import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeComponent } from './employee.component';

describe('EmployeComponent', () => {
  let component: EmployeComponent;
  let fixture: ComponentFixture<EmployeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeComponent]
    });
    fixture = TestBed.createComponent(EmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
