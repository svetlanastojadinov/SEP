import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BTCsuccessComponent } from './btcsuccess.component';

describe('BTCsuccessComponent', () => {
  let component: BTCsuccessComponent;
  let fixture: ComponentFixture<BTCsuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BTCsuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BTCsuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
