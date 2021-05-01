package com.epam.rd.java.basic.practice7.controllers;

import com.epam.rd.java.basic.practice7.constants.XMLConstants;
import com.epam.rd.java.basic.practice7.laptops.ColorPrices;
import com.epam.rd.java.basic.practice7.laptops.Laptops;
import com.epam.rd.java.basic.practice7.laptops.Parameters;
import com.epam.rd.java.basic.practice7.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

public class STAXController {
    private String xmlFilePath;
    Laptops laptop;
    ColorPrices colorPrices;
    Parameters parameters;
    List<Laptops> laptops;

    public STAXController(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
        laptops = new ArrayList<>();
    }

    public void parse() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new StreamSource(xmlFilePath));
            while (eventReader.hasNext()) {
                XMLEvent xmlEvent = eventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("laptop")) {
                        laptop = new Laptops();
                        continue;
                    } else if (startElement.getName().getLocalPart().equals("Brand")) {
                        xmlEvent = eventReader.nextEvent();
                        laptop.setBrand(xmlEvent.asCharacters().getData());
                        continue;
                    } else if (startElement.getName().getLocalPart().equals("Series")) {
                        xmlEvent = eventReader.nextEvent();
                        laptop.setSeries(xmlEvent.asCharacters().getData());
                        continue;
                    } else if (startElement.getName().getLocalPart().equals("Price")) {
                        xmlEvent = eventReader.nextEvent();
                        laptop.setPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        continue;
                    } else if (startElement.getName().getLocalPart().equals("ColorPrices")) {
                        colorPrices = new ColorPrices();
                        setColorPrices(eventReader);
                        continue;
                    } else if (startElement.getName().getLocalPart().equals("SalePrice")) {
                        xmlEvent = eventReader.nextEvent();
                        laptop.setSalePrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("Parameters")) {
                        parameters = new Parameters();
                        setParameters(eventReader);
                        continue;
                    }
                } else if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("laptop")) {
                        laptops.add(laptop);
                    } else if (endElement.getName().getLocalPart().equals("ColorPrices")) {
                        laptop.setColorPrices(colorPrices);
                    } else if (endElement.getName().getLocalPart().equals("Parameters")) {
                        laptop.setParameters(parameters);
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private void setColorPrices(XMLEventReader reader) {
        XMLEvent event;
        StartElement element;
        Characters character;
        for (int i = 0; i <= 7; i++) {
            try {
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    element = event.asStartElement();
                    if (element.getName().getLocalPart().equals("WithinNetworkCallPrice")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        colorPrices.setInBlackColor(Double.parseDouble(character.getData()));
                    } else if (element.getName().getLocalPart().equals("OutOfNetworkCallPrice")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        colorPrices.setInGreyColor(Double.parseDouble(character.getData()));
                    } else if (element.getName().getLocalPart().equals("LandLineNumCallPrice")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        colorPrices.setInWhiteColor(Double.parseDouble(character.getData()));
                    }
                }
            } catch (XMLStreamException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }


    private void setParameters(XMLEventReader reader) {
        XMLEvent event;
        StartElement element;
        Characters character;
        for (int i = 0; i <= 7; i++) {
            try {
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    element = event.asStartElement();
                    if (element.getName().getLocalPart().equals("NumberOfSeries")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        parameters.setNumberOfSeries(Integer.parseInt(character.getData()));
                    } else if (element.getName().getLocalPart().equals("ProcessorGeneration")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        parameters.setProcessorGen(Integer.parseInt(character.getData()));
                    } else if (element.getName().getLocalPart().equals("Display")) {
                        event = reader.nextEvent();
                        character = event.asCharacters();
                        parameters.setDisplay(Double.parseDouble(character.getData()));
                    }
                }
            } catch (XMLStreamException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    public void writeXML() {
        laptops.sort((o1, o2) -> o2.compareTo(o1));
        Util.writeSaxStax(laptops, XMLConstants.STAX_RESULT);
    }

    public static void main(String[] args) {
        STAXController parser = new STAXController("input.xml");
        parser.parse();
        parser.writeXML();
    }
}
