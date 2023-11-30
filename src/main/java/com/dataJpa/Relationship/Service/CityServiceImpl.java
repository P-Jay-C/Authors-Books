package com.dataJpa.Relationship.Service;

import com.dataJpa.Relationship.DTOs.RequestDto.CityRequestDto;
import com.dataJpa.Relationship.Models.City;
import com.dataJpa.Relationship.Repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository ) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());

        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {

        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);

        return cities;
    }

    @Override
    public City getCity(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(() ->
                new IllegalArgumentException("city with ciityId: "+cityId + "could not be found")
        );
    }

    @Override
    public City deleteCity(Long cityId) {
        City city = getCity(cityId);

        cityRepository.delete(city);
        return city;
    }

    @Transactional
    @Override
    public City editCity(Long cityId, CityRequestDto cityRequestDto) {

        City cityToEdit = getCity(cityId);
        cityToEdit.setName(cityRequestDto.getName());
        return null;
    }
}
