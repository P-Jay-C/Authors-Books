package com.dataJpa.Relationship.Repository;

import com.dataJpa.Relationship.Models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
}
