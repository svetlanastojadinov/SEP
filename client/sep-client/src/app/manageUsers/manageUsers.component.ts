import { Component, OnInit } from "@angular/core";
import { AuthService } from '../auth/auth.service';
import { Router } from "@angular/router";
@Component({
    selector: "app-manage-users",
    templateUrl: "./manageUsers.component.html",
    styleUrls: ["./manageUsers.component.css"]
})

export class ManageUsersComponent implements OnInit {
    private newUser: any = {};
    private user: string = '';
    constructor(private service: AuthService, private router: Router) { }
    ngOnInit() {

    }

    setUser(user: string) {
        this.user = user;
    }

    add(user: string) {
        if (user === 'author') {
            console.log('add author')
            this.newUser.password = this.newUser.username;
            this.service.registerAuthor(this.newUser).subscribe(
                data => {
                    alert('Successfully')
                    this.router.navigate(['/login']);
                },
                err => console.log(err)
            )
        }

        if (user === 'redactor') {
            console.log('add redactor')
            this.newUser.password = this.newUser.username;
            this.service.registerRedactor(this.newUser).subscribe(
                data => {
                    alert('Successfully')
                    this.router.navigate(['/login']);
                },
                err => console.log(err)
            )
        }

        console.log(this.newUser)
    }
}