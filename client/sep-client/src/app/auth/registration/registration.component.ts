import { Component, OnInit } from "@angular/core";
import { AuthService } from "../auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"],
  providers: [AuthService]
})
export class RegistrationComponent implements OnInit {
  private newUser: any = {};
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  register() {
    this.authService.register(this.newUser)
    .subscribe(
      data=>{
        alert('Successfully')
        this.router.navigate(['/login']);
      
      },
      err=>console.log(err)
    )
    
  }
}
