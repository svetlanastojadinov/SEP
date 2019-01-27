import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {
  
  constructor( private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }
}
