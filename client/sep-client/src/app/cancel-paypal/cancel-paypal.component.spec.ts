import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelPaypalComponent } from './cancel-paypal.component';

describe('CancelPaypalComponent', () => {
  let component: CancelPaypalComponent;
  let fixture: ComponentFixture<CancelPaypalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancelPaypalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelPaypalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
