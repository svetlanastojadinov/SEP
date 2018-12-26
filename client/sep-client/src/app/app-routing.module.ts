import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {PaypalsuccesComponent} from './paypalsucces/paypalsucces.component';
import {BTCsuccessComponent} from './btcsuccess/btcsuccess.component';
import { CancelPaypalComponent } from './cancel-paypal/cancel-paypal.component'
import { CancelBTCComponent } from './cancel-btc/cancel-btc.component';

const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: '', component: HomepageComponent,pathMatch: 'full'},
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