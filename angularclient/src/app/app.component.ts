import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ArrivalService} from "./_core/service/arrival.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Stop Point Arrival Time';
  stopPointId:string;
  stopPointMap = new Map();

  constructor(private route: ActivatedRoute, private router: Router,  private arrivalService: ArrivalService){}

  search(){
    this.arrivalService.findAll(this.stopPointId).subscribe(data => {
      this.stopPointMap.set(this.stopPointId, data);
    });
  }

  favourite(stopPointId:string){
    this.arrivalService.findAll(stopPointId).subscribe(data => {
      this.stopPointMap.set(stopPointId, data);
    });
  }

  remove(key:string){
    if (this.stopPointMap.has(key)){
      this.stopPointMap.delete(key);
    }
  }
}
