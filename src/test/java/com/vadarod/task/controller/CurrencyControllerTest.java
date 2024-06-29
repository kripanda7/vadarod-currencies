package com.vadarod.task.controller;

import com.vadarod.task.dto.CurrencyDto;
import com.vadarod.task.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTest {
    @InjectMocks
    CurrencyController currencyController;

    @Mock
    CurrencyService currencyService;

    @Test
    public void testGetCurrencies() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CurrencyDto currencyDto = CurrencyDto.builder().build();
        when(currencyService.getCurrencies(any(LocalDate.class))).thenReturn(list(currencyDto));

        List<CurrencyDto> response = currencyController.getCurrencies(LocalDate.now());

        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0)).isEqualTo(currencyDto);
    }

    @Test
    public void testGetCurrency() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CurrencyDto currencyDto = CurrencyDto.builder().build();
        when(currencyService.getCurrency(any(LocalDate.class), any(String.class))).thenReturn(currencyDto);

        CurrencyDto response = currencyController.getCurrenciesByCode(LocalDate.now(), "code");

        assertThat(response).isEqualTo(currencyDto);
    }
}
