package com.example.microservicesdemoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ValidationDto {
    @NotEmpty
    @Size(max = 10, message = "Name is too long")
    private String name;
    @Max(150)
    @PositiveOrZero
    private Long age;
}
