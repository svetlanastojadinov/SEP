import { TestBed, inject } from '@angular/core/testing';
import { PaymentCardService } from './payment.card.service';

describe('PaymentCardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PaymentCardService]
    });
  });

  it('should be created', inject([PaymentCardService], (service: PaymentCardService) => {
    expect(service).toBeTruthy();
  }));
});
