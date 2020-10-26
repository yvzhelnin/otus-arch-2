package ru.yvzhelnin.otus.warehouse.enums;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public enum EquipmentCategory {

    SNOWBOARD(SeasonType.WINTER),
    CROSS_COUNTRY_SKIING(SeasonType.WINTER),
    MOUNTAIN_SKIING(SeasonType.WINTER),
    BICYCLE(SeasonType.SUMMER),
    SCOOTER(SeasonType.SUMMER);

    private final SeasonType seasonType;

    EquipmentCategory(SeasonType seasonType) {
        this.seasonType = seasonType;
    }

    public static Collection<EquipmentCategory> getBySeason(SeasonType seasonType) {
        return Arrays.stream(values()).filter(e -> e.seasonType == seasonType).collect(Collectors.toList());
    }
}
