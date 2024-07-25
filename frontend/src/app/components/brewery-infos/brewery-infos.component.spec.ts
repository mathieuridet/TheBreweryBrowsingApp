import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreweryInfosComponent } from './brewery-infos.component';

describe('BreweryInfosComponent', () => {
  let component: BreweryInfosComponent;
  let fixture: ComponentFixture<BreweryInfosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BreweryInfosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreweryInfosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
