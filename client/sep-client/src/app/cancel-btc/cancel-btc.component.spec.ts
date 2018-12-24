import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelBTCComponent } from './cancel-btc.component';

describe('CancelBTCComponent', () => {
  let component: CancelBTCComponent;
  let fixture: ComponentFixture<CancelBTCComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancelBTCComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelBTCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
