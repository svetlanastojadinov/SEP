import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { PaypalsuccesComponent } from './paypalsucces/paypalsucces.component';
import { BTCsuccessComponent } from './btcsuccess/btcsuccess.component';
import { CancelPaypalComponent } from './cancel-paypal/cancel-paypal.component'
import { CancelBTCComponent } from './cancel-btc/cancel-btc.component';
import { CenterComponent } from './center/center.component';
import { LoginComponent } from "./auth/login/login.component";
import { RegistrationComponent } from "./auth/registration/registration.component";
import { CardSuccessComponent } from './card/cardsucess/cardsuccess.component';
import { ErrorComponent } from './card/error/error.component';
import { FailedComponent } from './card/failed/failed.component';
import { WrongdataComponent } from './card/wrongdata/wrongdata.component';
import { PaypalsuccesMembership } from './paypalsuccesMembership/paypalsuccesMembership.component';
import { RoleGuardService } from './guards/role-guard.service';
import { AuthGuardService } from './guards/auth-guard.service';
import { NotAuthGuardService } from './guards/not-auth-service';
import { ManageUsersComponent } from './manageUsers/manageUsers.component';
import { AvailableComponent } from './available/available.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent, canActivate: [NotAuthGuardService] },
    { path: 'registration', component: RegistrationComponent, canActivate: [NotAuthGuardService] },
    { path: 'homepage', component: HomepageComponent, canActivate: [AuthGuardService] },
    { path: 'center', component: CenterComponent },
    { path: 'available', component: AvailableComponent},
    { path: '', component: CenterComponent, pathMatch: 'full' },
    { path: 'paypalsucces', component: PaypalsuccesComponent, canActivate: [AuthGuardService] },
    { path: 'btcsuccess/:url', component: BTCsuccessComponent, canActivate: [AuthGuardService] },
    { path: 'cancelPaypal', component: CancelPaypalComponent, canActivate: [AuthGuardService] },
    { path: 'cancelbtc', component: CancelBTCComponent, canActivate: [AuthGuardService] },
    { path: 'cardsuccess', component: CardSuccessComponent, canActivate: [AuthGuardService] },
    { path: 'error', component: ErrorComponent, canActivate: [AuthGuardService] },
    { path: 'wrongdata', component: WrongdataComponent, canActivate: [AuthGuardService] },
    { path: 'failed', component: FailedComponent, canActivate: [AuthGuardService] },
    {
        path: 'paypalsuccesMembership', component: PaypalsuccesMembership,
        canActivate: [RoleGuardService], data: { expectedRole: 'UREDNIK' }
    },
    {
        path: 'manageUsers', component: ManageUsersComponent,
        canActivate: [RoleGuardService], data: { expectedRole: 'ADMIN' }
    }
];

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}