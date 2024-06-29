package com.vadarod.task.mapper;

import com.vadarod.task.dto.CurrencyDto;
import com.vadarod.task.entity.CurrencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {
    CurrencyEntity toEntity(CurrencyDto dto);

    CurrencyDto toDto(CurrencyEntity entity);
}
