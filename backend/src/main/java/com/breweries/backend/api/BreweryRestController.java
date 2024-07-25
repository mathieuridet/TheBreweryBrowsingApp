package com.breweries.backend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.breweries.backend.models.BreweryDto;
import com.breweries.backend.services.BreweryService;


@RestController
public class BreweryRestController {

    @Autowired
    BreweryService breweryService;

    @GetMapping("/breweries/all")
    @ResponseBody
    public List<BreweryDto> listBreweriesWithPagination(@RequestParam int offset, @RequestParam int pageSize) {
        return breweryService.listBreweriesWithPagination(offset, pageSize);
    }

    @GetMapping("/breweries/search")
    @ResponseBody
    public List<BreweryDto> searchByMulticriteriasWithPagination(@RequestParam String name, @RequestParam String city, 
    @RequestParam String state, @RequestParam String type, @RequestParam int offset, @RequestParam int pageSize) {
        return breweryService.searchBreweriesMultiCriteria(name, city, state, type, offset, pageSize);
    }

    @GetMapping("/breweries/favorites/{id}")
    @ResponseBody
    public boolean isInFavorites(@PathVariable String id) {
        return breweryService.isInFavorites(id);
    }

    @PostMapping("/breweries/favorites/add")
    public void addToFavorite(@RequestBody BreweryDto breweryDto) {
        breweryService.addToFavorite(breweryDto);
    }
    
    @GetMapping("/breweries/favorites")
    @ResponseBody
    public List<BreweryDto> listFavorites(@RequestParam int offset, @RequestParam int pageSize) {
        return breweryService.listFavorites(offset, pageSize);
    }

    @DeleteMapping("/breweries/favorites/remove/{id}")
    public void deleteFromFavorites(@PathVariable String id) {
        breweryService.deleteByApiID(id);
    }

}
