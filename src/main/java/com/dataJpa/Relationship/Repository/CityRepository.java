package com.dataJpa.Relationship.Repository;

import com.dataJpa.Relationship.Models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {

}
