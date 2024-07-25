package com.breweries.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BreweryDto {
    private String id;
    private String name;
    private String brewery_type;
    private String address_1;
    private String address_2;
    private String address_3;
    private String city;
    private String state_province;
    private String postal_code;
    private String country;
    private String longitude;
    private String latitude;
    private String phone;
    private String website_url;
    private String state;
    private String street;
    private boolean favorite;
    
}
