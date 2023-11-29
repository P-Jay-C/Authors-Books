package com.dataJpa.Relationship.DTOs.RequestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String name;
    private List<Long> authorsIds;
    private Long categoryId;
}
