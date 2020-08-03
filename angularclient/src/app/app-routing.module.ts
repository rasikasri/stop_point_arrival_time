import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StopPointComponent} from './stop-point/stop-point.component';

const routes: Routes = [
  { path: 'stop-point', component: StopPointComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
