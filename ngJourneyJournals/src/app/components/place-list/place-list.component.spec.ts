import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaceListComponent } from './place-list.component';

describe('PlaceListComponent', () => {
  let component: PlaceListComponent;
  let fixture: ComponentFixture<PlaceListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlaceListComponent]
    });
    fixture = TestBed.createComponent(PlaceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
