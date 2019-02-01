import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (request.url !== "api/auth/login") {
      const token = localStorage.getItem("token");
      if (token) {
        request = request.clone({
          headers: request.headers.set("Authorization", token)
        });
      }
    }
    return next.handle(request);
  }
}
