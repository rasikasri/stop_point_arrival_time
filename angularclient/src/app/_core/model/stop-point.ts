import {Arrival} from "./arrival";

export class StopPoint {
  naptanId: string;
  platformName: string;
  stationName: string;
  towards: string;
  arrivals: Arrival[];
}
