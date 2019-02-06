import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  constructor(private http: HttpClient) { }

  register(newUser: any) {
    return this.http.post("api/auth/register", newUser);
  }

  login(loginRequest: any) {
    return this.http.post("api/auth/login", loginRequest);
  }

  registerAuthor(newUser: any) {
    return this.http.post("api/auth/registerAuthor", newUser);
  }

  registerRedactor(newUser: any) {
    return this.http.post("api/auth/registerRedactor", newUser);
  }
}
