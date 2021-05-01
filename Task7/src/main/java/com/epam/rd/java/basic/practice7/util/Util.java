package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constants.LaptopConstants;
import com.epam.rd.java.basic.practice7.laptops.ColorPrices;
import com.epam.rd.java.basic.practice7.laptops.Laptops;
import com.epam.rd.java.basic.practice7.laptops.Parameters;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Object> laptopToList (Laptops laptop) {
        List<Object> result = new ArrayList<>();
        result.add(laptop.getBrand());
        result.add(laptop.getSeries());
        result.add(laptop.getPrice());
        result.add(laptop.getSalePrice());
        ColorPrices prices = laptop.getColorPrices();
        result.add(prices.getInBlackColor());
        result.add(prices.getInGreyColor());
        result.add(prices.getInWhiteColor());
        Parameters parameters = laptop.getParameters();
        result.add(parameters.getNumberOfSeries());
        result.add(parameters.getProcessorGen());
        result.add(parameters.getDisplay());
        return result;
    }

    public static void writeSaxStax(List<Laptops> laptops, String path) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = null;
        try (FileWriter fileWriter = new FileWriter(path)) {
            xmlStreamWriter = outputFactory.createXMLStreamWriter(fileWriter);
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement(LaptopConstants.LAPTOPS);

            for (Laptops laptopObj : laptops) {
                List<Object> listedLaptop = Util.laptopToList(laptopObj);
                List<Object> listedColorPrices = listedLaptop.subList(4, 7);
                List<Object> listedParameters = listedLaptop.subList(7, listedLaptop.size());
                xmlStreamWriter.writeStartElement(LaptopConstants.LAPTOP);
                for (int i = 0; i < 4; i++) {
                    xmlStreamWriter.writeStartElement(LaptopConstants.LAPTOP_ARRAY[i]);
                    xmlStreamWriter.writeCharacters(listedLaptop.get(i).toString());
                    xmlStreamWriter.writeEndElement();
                }
                writeColorPrices(xmlStreamWriter, listedColorPrices);
                writeParameters(xmlStreamWriter, listedParameters);
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void writeParameters(XMLStreamWriter writer, List<Object> listedParameters)
            throws XMLStreamException {
        writer.writeStartElement(LaptopConstants.PARAMETERS);
        for (int i = 0; i < listedParameters.size(); i++) {
            writer.writeStartElement(LaptopConstants.PARAMETERS_ARRAY[i]);
            writer.writeCharacters(listedParameters.get(i).toString());
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }

    private static void writeColorPrices(XMLStreamWriter writer, List<Object> listedColorPrices) {
        try {
            writer.writeStartElement(LaptopConstants.COLOR_PRICES);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listedColorPrices.size(); i++) {
            try {
                writer.writeStartElement(LaptopConstants.COLOR_PRICES_ARRAY[i]);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            try {
                writer.writeCharacters(listedColorPrices.get(i).toString());
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            try {
                writer.writeEndElement();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
