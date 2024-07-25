import { HttpClient, HttpParams } from "@angular/common/http";
import { Brewery } from "../models/Brewery";
import { Injectable } from "@angular/core";
import { off } from "process";

const BASE_URL = 'http://localhost:8080/breweries';

@Injectable({
    providedIn: 'root'
  })
export class api {

    constructor(private httpClient: HttpClient){}

    addToFavorite(body: any) {
        return this.httpClient.post<Brewery>(BASE_URL + '/favorites/add', body);
    }

    removeFromFavorite(id: any) {    
        return this.httpClient.delete(BASE_URL + '/favorites/remove/' + id);
    }

    listFavorites(offset: number, pageSize: number) {
        const options =  { params: new HttpParams().set('offset', offset).set('pageSize', pageSize) };
        return this.httpClient.get(BASE_URL + '/favorites', options);
    }

    listBreweries(offset: number, pageSize: number){
        const options =  { params: new HttpParams().set('offset', offset).set('pageSize', pageSize) };
        return this.httpClient.get(BASE_URL + '/all', options);
    }

    searchBreweries(name: any, city: any, state: any, type: any, offset: number, pageSize: number) {
        const options = name ? { params: new HttpParams()
            .set('name', name ? name : "")
            .set('city', city ? city : "")
            .set('state', state ? state : "")
            .set('type', type ? type : "")
            .set('offset', offset)
            .set('pageSize', pageSize) } : {};
            console.log(options);
        return this.httpClient.get(BASE_URL + '/search', options);
    }

    isInFavorites(apiID: string) {
        return this.httpClient.get(BASE_URL + '/favorites/' + apiID);
    }

}

