import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StopPointComponent } from './stop-point.component';

describe('StopPointComponent', () => {
  let component: StopPointComponent;
  let fixture: ComponentFixture<StopPointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StopPointComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StopPointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
