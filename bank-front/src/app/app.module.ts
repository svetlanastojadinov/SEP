import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { BankCardInfoComponent } from './bank-card-info/bank-card-info.component';
import { PaymentCardService } from './payment_card_service/payment.card.service';

@NgModule({
  declarations: [
    AppComponent,
    BankCardInfoComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PaymentCardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
