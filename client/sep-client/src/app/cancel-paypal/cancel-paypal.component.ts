import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-cancel-paypal',
  templateUrl: './cancel-paypal.component.html',
  styleUrls: ['./cancel-paypal.component.css']
})
export class CancelPaypalComponent implements OnInit {

  constructor( private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }

  backToCart(){
    this.router.navigate(['center']);
  }

}
