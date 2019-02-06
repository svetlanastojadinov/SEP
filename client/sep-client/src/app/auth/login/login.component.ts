import { Component, OnInit } from "@angular/core";
import { AuthService } from "../auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
  providers: [AuthService]
})
export class LoginComponent implements OnInit {
  private loginRequest: any = {};
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.loginRequest).subscribe(
      (data: any) => {
        localStorage.setItem("token", data.accessToken);
        localStorage.setItem("username", this.loginRequest.username);
        localStorage.setItem("role", data.role);

        this.router.navigate(["/center"]);
        window.location.reload();
      },
      err => {
        alert("Greska");
      }
    );
  }
}
