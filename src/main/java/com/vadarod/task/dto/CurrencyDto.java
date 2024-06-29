package com.vadarod.task.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CurrencyDto {
    private Long id;
    private Integer curId;
    private LocalDate date;
    private String abbreviation;
    private Integer scale;
    private String name;
    private Double officialRate;
}
