package com.vadarod.task.service;

import com.vadarod.task.dto.CurrencyDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getCurrencies(LocalDate date);

    CurrencyDto getCurrency(LocalDate date, String currencyCode);
}
