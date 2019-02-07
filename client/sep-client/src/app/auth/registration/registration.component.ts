import { Component, OnInit } from "@angular/core";
import { AuthService } from "../auth.service";
import { Router } from "@angular/router";
import { Validators } from "@angular/forms";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"],
  providers: [AuthService]
})
export class RegistrationComponent implements OnInit {
  private newUser: any = {};
  private newUserValid: any = {};
  private repeatPassword: string = "";
  private matchingPassword: boolean = true;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.newUser.username = "";
    this.newUser.email = "";
    this.newUser.name = "";
    this.newUser.surname = "";
    this.newUser.password = "";

    this.newUserValid.username = true
    this.newUserValid.email = true
    this.newUserValid.name = true
    this.newUserValid.surname = true
    this.newUserValid.password = true

  }
  validOnKeyPress(type: string) {
    this.newUserValid[type] = true;
  }
  register() {
    this.matchingPassword = true;
    if (this.newUser.password !== this.repeatPassword) {
      this.matchingPassword = false;
    } else {
      if (this.checkValid(this.newUser)) {
        this.authService.register(this.newUser)
          .subscribe(
            data => {
              alert('Successfully')
              this.router.navigate(['/login']);
            },
            err => console.log(err)
          )
      }

    }


  }

  checkValid(newUser) {
    this.newUserValid.username = newUser.username.trim().length >= 4;
    this.newUserValid.email = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/.test(newUser.email);
    this.newUserValid.name = newUser.name.trim().length >= 1;
    this.newUserValid.surname = newUser.surname.trim().length >= 1;
    this.newUserValid.password = newUser.password.trim().length >= 8;
    //Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
    return this.newUserValid.username
      || this.newUserValid.email
      || this.newUserValid.name
      || this.newUserValid.surname
      || this.newUserValid.password;
  }
}
