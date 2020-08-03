import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { StopPointComponent } from './stop-point/stop-point.component';
import {AppRoutingModule} from './app-routing.module';
import {ArrivalService} from './_core/service/arrival.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {PanelModule} from "primeng/panel";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    StopPointComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    PanelModule,
    NgbModule
  ],
  providers: [ArrivalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
