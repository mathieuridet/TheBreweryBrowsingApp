package com.breweries.backend.utils;

import com.breweries.backend.models.BreweryDto;
import com.breweries.backend.models.BreweryEntity;

public class BreweryUtils {

    public static BreweryDto convertBreweryEntityToDto(BreweryEntity entity) {
        BreweryDto dto = new BreweryDto();
        dto.setId(entity.getApiID());
        dto.setName(entity.getName());
        dto.setBrewery_type(entity.getType());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setFavorite(true);
        return dto;
    }
    
}
