import { Component, OnInit } from "@angular/core";
import { SharedService } from "./../shared_service/shared.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-center",
  templateUrl: "./center.component.html",
  styleUrls: ["./center.component.css"]
})
export class CenterComponent implements OnInit {
  private magazines: any = [];
  private articles: any = [];
  private logged: boolean = false;

  constructor(private sharedService: SharedService, private router: Router) {}

  ngOnInit() {
    this.logged = localStorage.getItem("token") !== null;

    this.sharedService.getMagazines().subscribe((data: any) => {
      this.magazines = data;
    });
    this.sharedService.getArticles().subscribe((data: any) => {
      this.articles = data;
    });
  }

  goToLogin() {
    this.router.navigate(["/login"]);
  }

  addToCart(item, id) {
    if(item==='magazine') {
      this.sharedService.addMagazine(id).subscribe(
        data=> {
          alert('Dodat casopis u korpu');
        },
        err=> {
          alert('GRESKA. VIDI KONZOLU')
          console.log(err)
        }
      )
    }

    if(item==='article') {
      this.sharedService.addArticle(id).subscribe(
        data=> {
          alert('Dodat artikl u korpu');
        },
        err=> {
          alert('GRESKA. VIDI KONZOLU')
          console.log(err)
        }
      )
    }
  }
}
