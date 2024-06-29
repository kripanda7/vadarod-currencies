package com.vadarod.task.service;

import com.vadarod.task.dto.CurrencyDto;
import com.vadarod.task.entity.CurrencyEntity;
import com.vadarod.task.mapper.CurrencyMapper;
import com.vadarod.task.repository.CurrencyRepository;
import com.vadarod.task.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {
    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Mock
    CurrencyRepository currencyRepository;
    @Mock
    CurrencyMapper currencyMapper;

    @Test
    public void testGetCurrencies() {
        when(currencyMapper.toEntity(any(CurrencyDto.class))).thenReturn(new CurrencyEntity());

        List<CurrencyDto> result = currencyService.getCurrencies(LocalDate.of(2023, 1, 10));
        assertThat(result.size()).isEqualTo(31);
    }

    @Test
    public void testGetCurrency() {
        when(currencyMapper.toEntity(any(CurrencyDto.class))).thenReturn(new CurrencyEntity());
        CurrencyDto expected = CurrencyDto.builder()
                .curId(456)
                .date(LocalDate.of(2023, 1, 10))
                .abbreviation("RUB")
                .scale(100)
                .name("Российских рублей")
                .officialRate(3.835)
                .build();
        when(currencyMapper.toDto(any())).thenReturn(expected);
        CurrencyDto result = currencyService.getCurrency(LocalDate.of(2023, 1, 10), "456");
        assertThat(result).isEqualTo(expected);
    }
}
