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
  private loading: boolean = true;
  private addingArticle: boolean = false;
  private addingMagazine: boolean = false;

  private clickedArticle: string = "";
  private clickedMagazine: string ="";

  constructor(private sharedService: SharedService, private router: Router) {}

  ngOnInit() {
    if (localStorage.getItem("token") === null) {
      this.logged = false;
    } else {
      this.logged = true;
    }

    this.sharedService.getMagazines().subscribe((data: any) => {
      this.magazines = data;
      this.loading = false;
    });
    this.sharedService.getArticles().subscribe((data: any) => {
      this.articles = data;
    });
  }

  goToLogin() {
    this.router.navigate(["/login"]);
  }

  addToCart(item, id) {
    if (item === "magazine") {
      this.clickedMagazine = id;
      this.addingMagazine = true;
      this.sharedService.addMagazine(id).subscribe(
        data => {
          this.addingMagazine = false;
          if (data === "MAGAZINE_EXISTS") {
            alert("Casopis se vec nalazi u korpi");
          } else {
            alert("Dodat casopis u korpu");
          }
        },
        err => {
          this.addingMagazine = false;
          alert("GRESKA. Casopis se verovatno nalazi vec u korpi");
          console.log(err);
        }
      );
    }

    if (item === "article") {
      this.clickedArticle = id;
      this.addingArticle = true;
      this.sharedService.addArticle(id).subscribe(
        data => {
          this.addingArticle = false;
          if (data === "MAGAZINE_EXISTS") {
            alert("Artikl se vec nalazi u korpit");
          } else {
            alert("Dodat artikl u korpu");
          }
        },
        err => {
          this.addingArticle = false;
          alert("GRESKA. Artikl se verovatno nalazi vec u korpi");
          console.log(err);
        }
      );
    }
  }

  read() {
    alert("Reading...");
  }
}
