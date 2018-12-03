import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';


const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: '', component: HomepageComponent,pathMatch: 'full'}

];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes)]
  })
  export class AppRoutingModule {
  }