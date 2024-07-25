export class Brewery {
    id: string | undefined;
    name: string | undefined;
    brewery_type: string | undefined;
    city: string | undefined;
    website_url: string | undefined;
    address_1: string | undefined;
    address_2: string | undefined;
    address_3: string | undefined;
    phone: string | undefined;
    opening_hours: string | undefined; // TODO : opening hours, does not exist in the API ?
    
    favorite: boolean = false;
}