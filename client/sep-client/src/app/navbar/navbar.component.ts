import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  private logged: boolean = false;
  private username: string = "";

  constructor(private router: Router) {}

  ngOnInit() {
    this.logged = localStorage.getItem("token") !== null;
    this.username = localStorage.getItem("username");
  }

  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    this.router.navigate(["/center"]);
    window.location.reload();
  }
}
