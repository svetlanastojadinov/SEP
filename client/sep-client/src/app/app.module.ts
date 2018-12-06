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
import { PaypalsuccesComponent } from './paypalsucces/paypalsucces.component'

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PaypalsuccesComponent
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
    PaymentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
