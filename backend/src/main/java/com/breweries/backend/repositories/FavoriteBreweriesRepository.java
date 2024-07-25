package com.breweries.backend.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breweries.backend.models.BreweryEntity;

@Repository
public interface FavoriteBreweriesRepository extends CrudRepository<BreweryEntity, Integer>{

    BreweryEntity findByApiID(String apiID);

}
