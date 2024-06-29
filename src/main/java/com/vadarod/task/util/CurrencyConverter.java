package com.vadarod.task.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vadarod.task.dto.CurrencyDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {

    public static List<CurrencyDto> convertList(String json) {
        List<CurrencyDto> list = new ArrayList<>();
        JsonObject[] jsonObjects = new Gson().fromJson(json, JsonObject[].class);
        for (JsonObject ob : jsonObjects) {
            list.add(createCurrencyObj(ob));
        }
        return list;
    }

    public static CurrencyDto convert(String json) {
        JsonObject jsonObjects = new Gson().fromJson(json, JsonObject.class);
        return createCurrencyObj(jsonObjects);
    }

    private static CurrencyDto createCurrencyObj(JsonObject ob) {
        if (ob != null && !ob.isJsonNull() && !ob.isEmpty() && ob.has("Cur_ID") && ob.has("Date") && ob.has("Cur_Abbreviation") && ob.has("Cur_Scale") && ob.has("Cur_Name") && ob.has("Cur_OfficialRate")) {
            return CurrencyDto.builder()
                    .curId(ob.get("Cur_ID").getAsInt())
                    .date(LocalDate.parse(ob.get("Date").getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss")))
                    .abbreviation(ob.get("Cur_Abbreviation").getAsString())
                    .scale(ob.get("Cur_Scale").getAsInt())
                    .name(ob.get("Cur_Name").getAsString())
                    .officialRate(ob.get("Cur_OfficialRate").getAsDouble())
                    .build();
        }
        return CurrencyDto.builder().build();
    }
}
