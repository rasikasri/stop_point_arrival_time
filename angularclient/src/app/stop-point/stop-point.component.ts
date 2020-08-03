import {Component, Input, OnInit} from '@angular/core';
import {StopPoint} from "../_core/model/stop-point";

@Component({
  selector: 'app-stop-point',
  templateUrl: './stop-point.component.html',
  styleUrls: ['./stop-point.component.css']
})
export class StopPointComponent implements OnInit {
  @Input()
  stopPoints: StopPoint[];

  constructor() { }


  ngOnInit(): void {
  }

  format(time:number){
    const minutes = parseInt(String(time / 60), 10);
    const seconds = time % 60;

    return minutes+ (seconds ? ' : '+seconds : '');
  }

}
