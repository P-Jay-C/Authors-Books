package com.dataJpa.Relationship.Repository;

import com.dataJpa.Relationship.Models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
