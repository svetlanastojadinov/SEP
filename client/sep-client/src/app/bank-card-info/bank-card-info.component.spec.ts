import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankCardInfoComponent } from './bank-card-info.component';

describe('BankCardInfoComponent', () => {
  let component: BankCardInfoComponent;
  let fixture: ComponentFixture<BankCardInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankCardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankCardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
