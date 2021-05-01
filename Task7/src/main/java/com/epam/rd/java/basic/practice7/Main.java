package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.controllers.DOMController;
import com.epam.rd.java.basic.practice7.controllers.SAXController;
import com.epam.rd.java.basic.practice7.controllers.STAXController;

public final class Main {

    public static void main(final String[] args) {
        DOMController domController = new DOMController(args[0]);
        domController.parseXML(true);
        domController.saveToXML();

        STAXController stAXController = new STAXController(args[0]);
        stAXController.parse();
        stAXController.writeXML();

        SAXController saxController = new SAXController(args[0]);
        saxController.parse(true);
        saxController.writeXML();
    }
}
