import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-failed',
  templateUrl: './failed.component.html',
  styleUrls: ['./failed.component.css']
})
export class FailedComponent implements OnInit {
  
  constructor( private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
