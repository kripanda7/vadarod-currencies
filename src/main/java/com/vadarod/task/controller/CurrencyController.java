package com.vadarod.task.controller;

import com.vadarod.task.dto.CurrencyDto;
import com.vadarod.task.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @Operation(
            summary = "Get currencies for a specific date",
            description = "Return currencies by given date"
    )
    @GetMapping
    public List<CurrencyDto> getCurrencies(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid LocalDate date) {
        return currencyService.getCurrencies(date);
    }

    @Operation(
            summary = "Get currency for a specific date",
            description = "Return currency by given date and currency code"
    )
    @GetMapping("/{code}")
    public CurrencyDto getCurrenciesByCode(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid LocalDate date, @PathVariable("code") String code) {
        return currencyService.getCurrency(date, code);
    }
}
