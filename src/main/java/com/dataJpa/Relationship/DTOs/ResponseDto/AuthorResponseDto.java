package com.dataJpa.Relationship.DTOs.ResponseDto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private List<String> booksNames;
    private String zipCode;
}
