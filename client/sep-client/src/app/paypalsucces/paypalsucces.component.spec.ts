import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalsuccesComponent } from './paypalsucces.component';

describe('PaypalsuccesComponent', () => {
  let component: PaypalsuccesComponent;
  let fixture: ComponentFixture<PaypalsuccesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaypalsuccesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaypalsuccesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
