import { Component, Input } from '@angular/core';
import { Brewery } from '../../models/Brewery';
import { NgIf } from '@angular/common';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-brewery-infos',
  standalone: true,
  imports: [NgIf, MatIcon],
  templateUrl: './brewery-infos.component.html',
  styleUrl: './brewery-infos.component.scss',
})
export class BreweryInfosComponent {

  @Input() brewery_infos: Brewery | undefined;
  
}
