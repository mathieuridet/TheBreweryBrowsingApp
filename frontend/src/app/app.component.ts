import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BreweryBrowsingComponent } from "./components/brewery-browsing/brewery-browsing.component";
import { Brewery } from './models/Brewery';
import { BreweryInfosComponent } from "./components/brewery-infos/brewery-infos.component";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, BreweryBrowsingComponent, BreweryInfosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Brewery Browsing';
  brewery_to_display: Brewery = new Brewery;
  baseUrl = 'http://localhost:8080/breweries';

  constructor(private httpClient: HttpClient){}

  setBreweryToDisplay (b: Brewery) {
    this.brewery_to_display = b;
    this.isInFavorites();
  }

  isInFavorites() {
    this.httpClient.get(this.baseUrl + '/favorites/' + this.brewery_to_display?.id).subscribe((res)=>{
      this.brewery_to_display.favorite = Boolean(res);
  });
  }

}
