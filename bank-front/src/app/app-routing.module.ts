import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { BankCardInfoComponent } from './bank-card-info/bank-card-info.component';

const routes: Routes = [
    {path: ':url',component: BankCardInfoComponent},
];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes)]
  })
  export class AppRoutingModule {
  }