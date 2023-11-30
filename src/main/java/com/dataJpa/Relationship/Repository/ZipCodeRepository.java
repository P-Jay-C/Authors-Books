package com.dataJpa.Relationship.Repository;

import com.dataJpa.Relationship.Models.ZipCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode,Long> {
}
