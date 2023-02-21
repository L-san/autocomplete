package ru.lsan.renue.autocomplete.handler.comparators;

import ch.randelshofer.fastdoubleparser.JavaDoubleParser;
import java.util.Comparator;

public class DigitsComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        double x1d = JavaDoubleParser.parseDouble(o1);
        double x2d = JavaDoubleParser.parseDouble(o2);
        return Double.compare(x1d, x2d);
    }

}
