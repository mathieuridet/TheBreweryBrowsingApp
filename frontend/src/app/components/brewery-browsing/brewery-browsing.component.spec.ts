import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreweryBrowsingComponent } from './brewery-browsing.component';

describe('BreweryBrowsingComponent', () => {
  let component: BreweryBrowsingComponent;
  let fixture: ComponentFixture<BreweryBrowsingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BreweryBrowsingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreweryBrowsingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
