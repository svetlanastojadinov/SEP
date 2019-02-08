import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class SharedService {


  constructor(private router: Router, private http: HttpClient) { }

  getOrders() {
    return this.http.get("/api/orders");
  }

  getOneOrder(token: string) {
    return this.http.get("/api/orders/token=" + token);
  }
  updateOrder(order: any){
    return this.http.put("/api/orders", order);
  }

  getArticles() {
    return this.http.get("/api/articles");
  }
  getOneArticle(id: number) {
    return this.http.get("/api/articles/" + id);
  }

  getMagazines() {
    return this.http.get("/api/magazines");
  }
  getOneMagazine(id: number) {
    return this.http.get("/api/magazines/" + id);
  }

  getPaymentMethos() {
    return this.http.get("/api/mpayment_methods");
  }

  buy(order: any) {
    return this.http.post("/api/orders/buy", order);
  }

  getCart() {
    return this.http.get("api/user/getCart");
  }

  addMagazine(id: any) {
    return this.http.put("api/user/addMagazineToCart/" + id, {});
  }
  getBoughtMagazines() {
    return this.http.get("api/user/getBoughtMagazines");
  }
  getBoughtArticles() {
    return this.http.get("api/user/getBoughtArticles");
  }

  addArticle(id: any) {
    return this.http.put("api/user/addArticleToCart/" + id, {});
  }

  removeArticle(id: any) {
    return this.http.delete("api/user/removeArticleFromCart/" + id);
  }

  removeMagazine(id: any) {
    return this.http.delete("api/user/removeMagazineFromCart/" + id);
  }

  payMembership(id: any) {
    return this.http.put("api/paypal/payMembership/" + id, {});
  }

  setMembership(id: any) {
    return this.http.put("api/paypal/setMembership/" + id, {});
  }
}
