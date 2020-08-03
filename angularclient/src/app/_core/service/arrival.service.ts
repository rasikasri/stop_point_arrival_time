import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Arrival} from '../model/arrival';
import {StopPoint} from "../model/stop-point";

@Injectable({
  providedIn: 'root'
})
export class ArrivalService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/arrivals/';
  }

  public findAll(stopPointId: string): Observable<StopPoint[]> {
    return this.http.get<StopPoint[]>(this.usersUrl + stopPointId);
  }
}
