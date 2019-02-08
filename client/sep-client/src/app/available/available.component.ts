import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared_service/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-available',
  templateUrl: './available.component.html',
  styleUrls: ['./available.component.css']
})
export class AvailableComponent implements OnInit {
  private boughtMagazines: any=[];

  private boughtArticles: any=[];
  
  private logged: boolean = false;
  private loading: boolean = true;

  constructor(private sharedService: SharedService, private router: Router) { }

  ngOnInit() {
    if (localStorage.getItem("token") === null) {
      this.logged = false;
    } else {
      this.logged = true;
    }
  
    this.sharedService.getBoughtMagazines().subscribe((data:any)=>{
      this.boughtMagazines=data;
      this.loading = false;
    })
    this.sharedService.getBoughtArticles().subscribe((data:any)=>{
      this.boughtArticles=data;
      
    })
  }

}
