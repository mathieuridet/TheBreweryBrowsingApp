import { Component, Output, EventEmitter } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Brewery } from '../../models/Brewery';
import { AsyncPipe, CommonModule, DecimalPipe, NgFor } from '@angular/common';
import { NgbHighlight, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { api } from '../../api/api';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonModule} from '@angular/material/button';

export const PAGE_SIZE = 8;

interface BreweryType {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-brewery-browsing',
  standalone: true,
  imports: [NgFor, NgbModule, CommonModule, DecimalPipe, AsyncPipe, ReactiveFormsModule, 
    NgbHighlight, MatPaginatorModule, FormsModule, MatIconModule, MatInputModule, MatSelectModule, MatFormFieldModule,
    MatTooltipModule, MatButtonModule],
  providers: [DecimalPipe],
  templateUrl: './brewery-browsing.component.html',
  styleUrl: './brewery-browsing.component.scss'
})
export class BreweryBrowsingComponent {

  @Output() brewery_infos = new EventEmitter<Brewery>();

  searched_breweries: any;
  search_name: string | undefined;
  search_type: string | undefined;
  search_city: string | undefined;
  search_state: string | undefined;

  baseUrl = 'http://localhost:8080/breweries';
  currentPage = 0;
  totalSize = 0;
  count_breweries: any;
  listFavoritesMode = false;

  types: BreweryType[] = [
    {value: 'micro', viewValue: 'micro'},
    {value: 'nano', viewValue: 'nano'},
    {value: 'regional', viewValue: 'regional'},
    {value: 'brewpub', viewValue: 'brewpub'},
    {value: 'large', viewValue: 'large'},
    {value: 'planning', viewValue: 'planning'},
    {value: 'bar', viewValue: 'bar'},
    {value: 'contract', viewValue: 'contract'},
    {value: 'proprietor', viewValue: 'proprietor'},
    {value: 'closed', viewValue: 'closed'}
  ];
  
  constructor(private api: api){
    this.listBreweries();
  }

  async listBreweries(){
    this.listFavoritesMode = false;
    this.search_name = undefined;

    this.api.listBreweries(this.currentPage, PAGE_SIZE).subscribe((res)=>{
      this.searched_breweries = res;

      // For each brewery found, we go check if it's in our favorite ones
      for(let b of this.searched_breweries) {
        this.api.isInFavorites(b.id).subscribe((res) => {
          b.favorite = res;
        });
      }
    });
  }

  searchBreweries() {
    this.listFavoritesMode = false;

    if(this.search_name) this.search_name.trim();

    this.api.searchBreweries(this.search_name, this.search_city, this.search_state, this.search_type, 
      this.currentPage, PAGE_SIZE).subscribe((res)=>{
      this.searched_breweries = res;

      // For each brewery found, we go check if it's in our favorite ones
      for(let b of this.searched_breweries) {
        this.api.isInFavorites(b.id).subscribe((res) => {
          b.favorite = res;
        });
      }
    });
  }

  handlePage(e: any) {
    this.currentPage = e.pageIndex + 1;

    if(this.search_name !== undefined && this.search_name !== "") {
      this.searchBreweries();
    } else {
      this.listBreweries();
    }
  }

  resetSearch() {
    window.location.reload();
  }

  listFavorites() {
    this.listFavoritesMode = true;
    this.search_name = undefined;

    this.api.listFavorites(this.currentPage, PAGE_SIZE).subscribe((res)=>{
      this.searched_breweries = res;
    });
  }

  addToFavorite(brewery: Brewery) {
    const body = { id: brewery.id, name: brewery.name, 
      brewery_type: brewery.brewery_type, city: brewery.city, 
      address_1: brewery.address_1, website_url: brewery.website_url,
      phone: brewery.phone
     };
    this.api.addToFavorite(body).subscribe({
      next: data => {
          console.log('Add to favorite successful');
          if(this.search_name !== undefined && this.search_name !== "") {
            this.searchBreweries();
          } else {
            this.listBreweries();
          }
      },
      error: error => {
          console.error('Error during add :', error);
      }
    });

  }

  removeFromFavorite(brewery: Brewery) {
    this.api.removeFromFavorite(brewery.id).subscribe({
      next: data => {
          console.log('Delete successful');

          if(this.listFavoritesMode) {
            this.listFavorites();
          } else if(this.search_name !== undefined && this.search_name !== "") {
            // TODO : beug ici !!!
            // TODO : beug sur certaines breweries qui sont favorite mais sont toujours en "+" et impossible de les modifier
            this.searchBreweries();
          } else {
            this.listBreweries();
          }
      },
      error: error => {
          console.error('Error during delete :', error);
      }
    });
  }

  displayBrewery(b: Brewery) {
    this.brewery_infos.emit(b);
  }

}
