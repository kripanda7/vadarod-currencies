package com.vadarod.task.service.impl;

import com.vadarod.task.dto.CurrencyDto;
import com.vadarod.task.mapper.CurrencyMapper;
import com.vadarod.task.repository.CurrencyRepository;
import com.vadarod.task.service.CurrencyService;
import com.vadarod.task.util.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    private static final String URL = "https://api.nbrb.by/exrates/rates";

    @SneakyThrows
    @Override
    public List<CurrencyDto> getCurrencies(LocalDate date) {
        URIBuilder uriBuilder = new URIBuilder(URL);
        uriBuilder.addParameter("ondate", date.toString());
        uriBuilder.addParameter("periodicity", "0");
        List<CurrencyDto> list = CurrencyConverter.convertList(currencyRequest(uriBuilder));
        List<CurrencyDto> savedList = new ArrayList<>();
        for (CurrencyDto currencyDto : list) {
            savedList.add(currencyMapper.toDto(currencyRepository.save(currencyMapper.toEntity(currencyDto))));
        }
        return savedList;
    }

    @SneakyThrows
    @Override
    public CurrencyDto getCurrency(LocalDate date, String currencyCode) {
        URIBuilder uriBuilder = new URIBuilder(URL + "/" + currencyCode);
        uriBuilder.addParameter("ondate", date.toString());
        uriBuilder.addParameter("periodicity", "0");
        CurrencyDto currencyDto = CurrencyConverter.convert(currencyRequest(uriBuilder));
        return currencyMapper.toDto(currencyRepository.save(currencyMapper.toEntity(currencyDto)));
    }

    @SneakyThrows
    private String currencyRequest(URIBuilder uriBuilder) {
        URI uri = uriBuilder.build();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
