package ru.lsan.renue.autocomplete.handler;

import ru.lsan.renue.autocomplete.io.CSVSearch;

import java.util.List;

public class ProcessHandler {

    public static void handle(String pattern, int nCol, String path) {
        long start = System.currentTimeMillis();
        List<String> list = CSVSearch.search(path, nCol, pattern);
        long stop = System.currentTimeMillis();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("Количество найденных строк: " + list.size() + " " +
                "Время, затраченное на поиск: " + (stop - start) + " мс");
    }

}