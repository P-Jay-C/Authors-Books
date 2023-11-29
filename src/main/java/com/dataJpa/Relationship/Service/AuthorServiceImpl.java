package com.dataJpa.Relationship.Service;

import com.dataJpa.Relationship.DTOs.Mapper;
import com.dataJpa.Relationship.DTOs.RequestDto.AuthorRequestDto;
import com.dataJpa.Relationship.DTOs.ResponseDto.AuthorResponseDto;
import com.dataJpa.Relationship.Models.Author;
import com.dataJpa.Relationship.Models.ZipCode;
import com.dataJpa.Relationship.Repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ZipCodeService zipCodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipCodeService zipCodeService) {
        this.authorRepository = authorRepository;

        this.zipCodeService = zipCodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {

        Author author  = new Author();

        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipCodeId() == null){
            throw new IllegalArgumentException(
                    "Author needs a zipcode"
            );
        }

        ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
        author.setZipCode(zipCode);
        authorRepository.save(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {

        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return Mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return Mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {

        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException(
                "author with id: " + authorId + " could not be found"
        ));

        return author;
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);

        authorRepository.delete(author);

        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {

        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());

        if(authorRequestDto.getZipCodeId() != null){
            ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
            authorToEdit.setZipCode(zipCode);
        }

        return Mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipCodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipCodeService.getZipCode(zipCodeId);

        if(Objects.nonNull(author.getZipCode())){
            throw new IllegalArgumentException("author already has a zipcode");

        }
        author.setZipCode(zipCode);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipCodeFromAuthor(Long authorId) {

        Author author = getAuthor(authorId);
        author.setZipCode(null);

        return Mapper.authorToAuthorResponseDto(author);
    }
}
