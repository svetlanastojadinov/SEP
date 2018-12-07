import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {PaypalsuccesComponent} from './paypalsucces/paypalsucces.component';
import { CancelPaypalComponent } from './cancel-paypal/cancel-paypal.component'

const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: '', component: HomepageComponent,pathMatch: 'full'},
    {path: 'paypalsucces', component: PaypalsuccesComponent},
    {path: 'cancelPaypal', component: CancelPaypalComponent}
    

];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes)]
  })
  export class AppRoutingModule {
  }