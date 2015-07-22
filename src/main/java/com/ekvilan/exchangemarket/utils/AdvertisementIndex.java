package com.ekvilan.exchangemarket.utils;


public enum AdvertisementIndex {
    DATE_INDEX(0), RATE_INDEX(1), SUM_INDEX(2), PHONE_INDEX(3), AREA_INDEX(4), COMMENT_INDEX(5);

    private int value;

    AdvertisementIndex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
