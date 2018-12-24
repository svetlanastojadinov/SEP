import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-cancel-btc',
  templateUrl: './cancel-btc.component.html',
  styleUrls: ['./cancel-btc.component.css']
})
export class CancelBTCComponent implements OnInit {

  constructor( private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
