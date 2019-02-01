<<<<<<< HEAD
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { HomepageComponent } from "./homepage/homepage.component";
import { SharedService } from "./shared_service/shared.service";
import { PaymentService } from "./payment_service/payment.service";
import { PaypalsuccesComponent } from "./paypalsucces/paypalsucces.component";
import { CancelPaypalComponent } from "./cancel-paypal/cancel-paypal.component";
import { BTCsuccessComponent } from "./btcsuccess/btcsuccess.component";
import { PaymentBTCService } from "./payment_btc_service/payment.btc.service";
import { CancelBTCComponent } from "./cancel-btc/cancel-btc.component";
import { PaymentCardService } from "./payment_card_service/payment.card.service";
import { CenterComponent } from "./center/center.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { LoginComponent } from "./auth/login/login.component";
import { RegistrationComponent } from "./auth/registration/registration.component";
import {TokenInterceptor} from './auth/token-interceptor';
=======
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { HomepageComponent } from './homepage/homepage.component';
import {SharedService} from './shared_service/shared.service';
import {PaymentService} from './payment_service/payment.service';
import { PaypalsuccesComponent } from './paypalsucces/paypalsucces.component';
import { CancelPaypalComponent } from './cancel-paypal/cancel-paypal.component'
import { BTCsuccessComponent } from './btcsuccess/btcsuccess.component';
import { PaymentBTCService } from './payment_btc_service/payment.btc.service';
import { CancelBTCComponent } from './cancel-btc/cancel-btc.component';
import { PaymentCardService } from './payment_card_service/payment.card.service';
import { CardSuccessComponent } from './card/cardsucess/cardsuccess.component';
import { WrongdataComponent } from './card/wrongdata/wrongdata.component';
import { FailedComponent } from './card/failed/failed.component';
import { ErrorComponent } from './card/error/error.component';
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PaypalsuccesComponent,
    CancelPaypalComponent,
    BTCsuccessComponent,
    CancelBTCComponent,
<<<<<<< HEAD
    CenterComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent
=======
    CardSuccessComponent,
    WrongdataComponent,
    FailedComponent,
    ErrorComponent
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    SharedService,
    PaymentService,
    PaymentBTCService,
    PaymentCardService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
