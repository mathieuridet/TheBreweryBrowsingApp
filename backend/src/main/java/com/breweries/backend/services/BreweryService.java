package com.breweries.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.breweries.backend.models.BreweryDto;
import com.breweries.backend.models.BreweryEntity;
import com.breweries.backend.repositories.FavoriteBreweriesRepository;
import com.breweries.backend.utils.BreweryUtils;

@Service
public class BreweryService {
    private static final String API_URI = "https://api.openbrewerydb.org/v1/breweries";

    RestClient restClient = RestClient.create();

    @Autowired
    private FavoriteBreweriesRepository repository;

    public List<BreweryDto> listBreweriesWithPagination(int offset, int pageSize) {
        List<BreweryDto> breweries = restClient.get()
                    .uri(API_URI + "?page={offset}&per_page={pageSize}", offset, pageSize)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
    
        return breweries;
    }

    private final static String EMPTY = "";

    public List<BreweryDto> searchBreweriesMultiCriteria(String name, String city, String state, String type, int offset, int pageSize) {
        
        StringBuilder url = new StringBuilder(API_URI + "?page={offset}&per_page={pageSize}");

        if(!name.equals(EMPTY)) url.append("&by_name={name}");
        if(!city.equals(EMPTY)) url.append("&by_city={city}");
        if(!state.equals(EMPTY)) url.append("&by_state={state}");
        if(!type.equals(EMPTY)) url.append("&by_type={type}");

        List<BreweryDto> breweries = null;
        if(!name.equals(EMPTY)) {
            if(!city.equals(EMPTY)) {
                if(!state.equals(EMPTY)) {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, city, state, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, city, state)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                } else {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, city, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, city)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                }
            } else {
                if(!state.equals(EMPTY)) {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, state, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, state)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                } else {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, name)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                }
            }
        } else {
            if(!city.equals(EMPTY)) {
                if(!state.equals(EMPTY)) {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, city, state, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, city, state)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                } else {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, city, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, city)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                }
            } else {
                if(!state.equals(EMPTY)) {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, state, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    } else {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, state)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                } else {
                    if(!type.equals(EMPTY)) {
                        breweries = restClient.get()
                        .uri(url.toString(), offset, pageSize, type)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});
                    }
                }
            }
        }

        return breweries;
    }

    public List<BreweryDto> listFavorites(int offset, int pageSize) {
        Iterable<BreweryEntity> entities = repository.findAll();
        List<BreweryDto> dtos = new ArrayList<>();

        for(BreweryEntity b : entities) {
            dtos.add(BreweryUtils.convertBreweryEntityToDto(b));
        }
        return dtos;
    }

    public boolean isInFavorites(String id) {
        return repository.findByApiID(id) != null;
    }

    public void addToFavorite(BreweryDto breweryDto) {
        BreweryEntity entity = new BreweryEntity();
        entity.setApiID(breweryDto.getId());
        entity.setName(breweryDto.getName());
        entity.setType(breweryDto.getBrewery_type());
        entity.setCity(breweryDto.getCity());
        entity.setState(breweryDto.getState());
        entity.setPhone(breweryDto.getPhone());
        entity.setWebsite_url(breweryDto.getWebsite_url());
        entity.setAddress(breweryDto.getAddress_1());

        repository.save(entity);
    }

    public void deleteByApiID(String id) {
        System.out.println("### deleteByApiID ###");
        repository.delete(repository.findByApiID(id));
    }

}
