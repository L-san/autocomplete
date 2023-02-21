package ru.lsan.renue.autocomplete.handler.comparators;

import java.util.Comparator;

public class LineComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }

}
