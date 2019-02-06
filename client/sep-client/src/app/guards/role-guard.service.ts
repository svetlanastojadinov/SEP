import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import * as decode from 'jwt-decode';


@Injectable()
export class RoleGuardService implements CanActivate {

    constructor(public router: Router) { }

    isAuthenticated() {
        return localStorage.getItem('token') !== null;
    }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const expectedRole = route.data.expectedRole;
        if (!this.isAuthenticated()) {
            console.log('To login')
            this.router.navigate(['login']);
            return false;
        }

        if (this.isAuthenticated() && localStorage.getItem('role') !== expectedRole) {
            this.router.navigate(['center']);
            return false;
        }
        return true;
    }
}