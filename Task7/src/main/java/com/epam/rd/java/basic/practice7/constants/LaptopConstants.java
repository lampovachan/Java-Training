package com.epam.rd.java.basic.practice7.constants;

public class LaptopConstants {
    public static final String LAPTOPS = "laptops";
    public static final String LAPTOP = "laptop";
    public static final String BRAND = "Brand";
    public static final String SERIES = "Series";
    public static final String PRICE = "Price";
    public static final String SALE_PRICE = "SalePrice";
    public static final String COLOR_PRICES = "ColorPrices";
    public static final String IN_BLACK_COLOR_PRICE = "InBlackColor";
    public static final String IN_GREY_COLOR_PRICE = "InGreyColor";
    public static final String IN_WHITE_COLOR_PRICE = "InWhiteColor";
    public static final String PARAMETERS = "Parameters";
    public static final String NUMBER_OF_SERIES = "NumberOfSeries";
    public static final String PROCESSOR_GEN = "ProcessorGen";
    public static final String DISPLAY = "Display";

    public static final String[] LAPTOP_ARRAY = {BRAND, SERIES, PRICE, SALE_PRICE, COLOR_PRICES,
            PARAMETERS };
    public static final String[] PARAMETERS_ARRAY = {NUMBER_OF_SERIES, PROCESSOR_GEN, DISPLAY};
    public static final String[] COLOR_PRICES_ARRAY = {IN_BLACK_COLOR_PRICE, IN_GREY_COLOR_PRICE,
            IN_WHITE_COLOR_PRICE};

    private LaptopConstants() {
        throw new IllegalStateException("Utility class LaptopConstants");
    }
}
