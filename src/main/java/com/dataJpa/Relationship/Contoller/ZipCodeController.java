package com.dataJpa.Relationship.Contoller;
import com.dataJpa.Relationship.DTOs.RequestDto.ZipCodeRequestDto;
import com.dataJpa.Relationship.Models.ZipCode;
import com.dataJpa.Relationship.Service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipCodeController {


    private final ZipCodeService zipCodeService;

    @Autowired
    public ZipCodeController(ZipCodeService zipCodeService) {

        this.zipCodeService = zipCodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<ZipCode> addZipcode(ZipCodeRequestDto zipCodeRequestDto){

        ZipCode zipCode = zipCodeService.addZipCode(zipCodeRequestDto);

        return new  ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<ZipCode> getZipcode(@PathVariable final Long id){

    ZipCode zipCode = zipCodeService.getZipCode(id);

        return new ResponseEntity<>(zipCode,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public  ResponseEntity<List<ZipCode>> getZipcodes(){

        List<ZipCode> zipCodes = zipCodeService.getZipCodes();

        return new ResponseEntity<>(zipCodes,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ZipCode> deleteZipcode(@PathVariable final Long id){

        ZipCode zipCode = zipCodeService.deleteZipCode(id);

        return new ResponseEntity<>(zipCode,HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public  ResponseEntity<ZipCode> editZipcode(@RequestBody final ZipCodeRequestDto zipCodeRequestDto, @PathVariable final Long id){

        ZipCode zipCode = zipCodeService.editZipCode(id,zipCodeRequestDto);
        return new ResponseEntity<>(zipCode,HttpStatus.OK);


    }

    @PostMapping("/addCity/cityId/toZipCode/zipCodeId")
    public ResponseEntity<ZipCode> addCity(@PathVariable final Long zipCodeId, @PathVariable final Long cityId){

        ZipCode zipCode = zipCodeService.addCityToZipCode(zipCodeId,cityId);

        return new ResponseEntity<>(zipCode,HttpStatus.OK);
    }

    @PostMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<ZipCode> deleteCity(@PathVariable final Long zipcodeId){

        ZipCode zipCode = zipCodeService.removeCityFromZipCode(zipcodeId);
        return new ResponseEntity<>(zipCode,HttpStatus.OK);
    }

}
