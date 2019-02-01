import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {PaypalsuccesComponent} from './paypalsucces/paypalsucces.component';
import {BTCsuccessComponent} from './btcsuccess/btcsuccess.component';
import { CancelPaypalComponent } from './cancel-paypal/cancel-paypal.component'
import { CancelBTCComponent } from './cancel-btc/cancel-btc.component';
import { CenterComponent } from './center/center.component';
import { LoginComponent } from "./auth/login/login.component";
import { RegistrationComponent } from "./auth/registration/registration.component";

const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'homepage', component: HomepageComponent},
    {path: 'center', component: CenterComponent},
    {path: '', component: CenterComponent,pathMatch: 'full'},
    {path: 'paypalsucces', component: PaypalsuccesComponent},
    {path: 'btcsuccess', component: BTCsuccessComponent},
    {path: 'cancelPaypal', component: CancelPaypalComponent},
    {path: 'cancelbtc', component: CancelBTCComponent}

];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes)]
  })
  export class AppRoutingModule {
  }