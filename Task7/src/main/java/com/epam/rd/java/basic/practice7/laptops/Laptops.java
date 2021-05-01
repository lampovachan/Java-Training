package com.epam.rd.java.basic.practice7.laptops;

public class Laptops implements Comparable<Laptops>{
    private String brand;
    private String series;
    private double price;
    private double salePrice;
    private ColorPrices colorPrices;
    private Parameters parameters;

    public Laptops(String brand, String series, double price, double salePrice) {
        this.brand = brand;
        this.series = series;
        this.price = price;
        this.salePrice = salePrice;
    }

    public Laptops() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public ColorPrices getColorPrices() {
        return colorPrices;
    }

    public void setColorPrices(ColorPrices colorPrices) {
        this.colorPrices = colorPrices;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toString() {
        return "-------------------------\n" + "Laptops brand: " + brand + "\nLaptops series: " + series
                + "\nPrice: " + price + "\n" + String.valueOf(colorPrices) + "\nSale price: " + salePrice + "\n"
                + String.valueOf(parameters);
    }

    @Override
    public int compareTo(Laptops o) {
        int result = brand.compareTo(o.brand);
        if (result != 0) {
            return result;
        }
        result = o.brand.compareTo(brand);
        if (result != 0) {
            return result;
        }
        result = series.compareTo(o.series);
        if (result != 0) {
            return result;
        }
        result = o.series.compareTo(series);
        if (result != 0) {
            return result;
        }
        result = Double.compare(price, o.price);
        if (result != 0) {
            return result;
        }
        result = Double.compare(salePrice, o.salePrice);
        if (result != 0) {
            return result;
        }
        result = colorPrices.compareTo(o.colorPrices);
        if (result != 0) {
            return result;
        }
        result = parameters.compareTo(o.parameters);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
