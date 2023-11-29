package com.dataJpa.Relationship.Service;

import com.dataJpa.Relationship.DTOs.RequestDto.ZipCodeRequestDto;
import com.dataJpa.Relationship.Models.City;
import com.dataJpa.Relationship.Models.ZipCode;
import com.dataJpa.Relationship.Repository.ZipCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;

    @Autowired
    public ZipCodeServiceImpl(ZipCodeRepository zipCodeRepository, CityService cityService) {
        this.zipCodeRepository = zipCodeRepository;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = new ZipCode();
        zipCode.setName(zipCodeRequestDto.getName());
        if(zipCodeRequestDto.getCityId() == null){
            return zipCodeRepository.save(zipCode);
        }

        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCode.setCity(city);

        return zipCodeRepository.save(zipCode);
    }

    @Override
    public List<ZipCode> getZipCodes() {
        return StreamSupport
                .stream(zipCodeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public ZipCode getZipCode(Long zipCodeId) {
        return zipCodeRepository.findById(zipCodeId).orElseThrow(()->
                new IllegalArgumentException(
                        "zipcode with id:" + zipCodeId +
                                " could not be found"));
    }

    @Override
    public ZipCode deleteZipCode(Long zipCodeId) {
        ZipCode zipcode = getZipCode(zipCodeId);
        zipCodeRepository.delete(zipcode);

        return zipcode;
    }

    @Override
    public ZipCode editZipCode(Long zipCodeId, ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCodeToEdit = getZipCode(zipCodeId);
        zipCodeToEdit.setName(zipCodeRequestDto.getName());

        if (zipCodeRequestDto.getCityId() == null) {
            return zipCodeToEdit;
        }

        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCodeToEdit.setCity(city);

        return zipCodeToEdit;
    }


    @Override
    public ZipCode addCityToZipCode(Long zipCodeId, Long cityId) {

        ZipCode zipCode = getZipCode(zipCodeId);
        City city = cityService.getCity(cityId);

        if(Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode already has a city");
        }
        zipCode.setCity(city);
        return zipCode;
    }

    @Override
    public ZipCode removeCityFromZipCode(Long zipCodeId) {

        ZipCode zipCode = getZipCode(zipCodeId);
        if(!Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode does not have a cit");
        }
        zipCode.setCity(null);

        return zipCode;
    }
}
