import { TestBed, inject } from '@angular/core/testing';

import { PaymentBTCService } from './payment.btc.service';

describe('PaymentBTCService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PaymentBTCService]
    });
  });

  it('should be created', inject([PaymentBTCService], (service: PaymentBTCService) => {
    expect(service).toBeTruthy();
  }));
});
