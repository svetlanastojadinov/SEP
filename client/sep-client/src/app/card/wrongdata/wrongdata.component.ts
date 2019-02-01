import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-wrongdata',
  templateUrl: './wrongdata.component.html',
  styleUrls: ['./wrongdata.component.css']
})
export class WrongdataComponent implements OnInit {

  constructor( private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
