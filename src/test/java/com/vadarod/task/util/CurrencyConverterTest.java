package com.vadarod.task.util;

import com.vadarod.task.dto.CurrencyDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

public class CurrencyConverterTest {
    @Test
    public void testConvertList() {
        String json = "[\n" +
                "  {\n" +
                "    \"Cur_ID\": 507,\n" +
                "    \"Date\": \"2023-01-10T00:00:00\",\n" +
                "    \"Cur_Abbreviation\": \"AZN\",\n" +
                "    \"Cur_Scale\": 1,\n" +
                "    \"Cur_Name\": \"Азербайджанский манат\",\n" +
                "    \"Cur_OfficialRate\": 1.6096\n" +
                "  },\n" +
                "  {\n" +
                "    \"Cur_ID\": 465,\n" +
                "    \"Date\": \"2023-01-10T00:00:00\",\n" +
                "    \"Cur_Abbreviation\": \"DZD\",\n" +
                "    \"Cur_Scale\": 100,\n" +
                "    \"Cur_Name\": \"Алжирских динаров\",\n" +
                "    \"Cur_OfficialRate\": 1.9927\n" +
                "  }]";
        List<CurrencyDto> expectedResult = list(
                CurrencyDto.builder()
                        .curId(507)
                        .date(LocalDate.of(2023, 1, 10))
                        .abbreviation("AZN")
                        .scale(1)
                        .name("Азербайджанский манат")
                        .officialRate(1.6096)
                        .build(),
                CurrencyDto.builder()
                        .curId(465)
                        .date(LocalDate.of(2023, 1, 10))
                        .abbreviation("DZD")
                        .scale(100)
                        .name("Алжирских динаров")
                        .officialRate(1.9927)
                        .build()
        );
        List<CurrencyDto> result = CurrencyConverter.convertList(json);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testConvertList_whenJsonIncorrect() {
        String json = "[\n" +
                "  {\n" +
                "    \"Cur_ID\": 507\n" +
                "  },\n" +
                "  {\n" +
                "    \"Date\": \"2023-01-10T00:00:00\"\n" +
                "  }]";
        List<CurrencyDto> expectedResult = list(
                CurrencyDto.builder().build(),
                CurrencyDto.builder().build()
        );
        List<CurrencyDto> result = CurrencyConverter.convertList(json);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testConvert() {
        String json = "\n" +
                "  {\n" +
                "    \"Cur_ID\": 507,\n" +
                "    \"Date\": \"2023-01-10T00:00:00\",\n" +
                "    \"Cur_Abbreviation\": \"AZN\",\n" +
                "    \"Cur_Scale\": 1,\n" +
                "    \"Cur_Name\": \"Азербайджанский манат\",\n" +
                "    \"Cur_OfficialRate\": 1.6096\n" +
                "  }";
        CurrencyDto expectedResult = CurrencyDto.builder()
                .curId(507)
                .date(LocalDate.of(2023, 1, 10))
                .abbreviation("AZN")
                .scale(1)
                .name("Азербайджанский манат")
                .officialRate(1.6096)
                .build();
        CurrencyDto result = CurrencyConverter.convert(json);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testConvert_whenJsonIncorrect() {
        String json = "\n" +
                "  {\n" +
                "    \"Cur_ID\": 507\n" +
                "  }";
        CurrencyDto expectedResult = CurrencyDto.builder().build();
        CurrencyDto result = CurrencyConverter.convert(json);
        assertThat(result).isEqualTo(expectedResult);
    }
}
