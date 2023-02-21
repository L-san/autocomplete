package ru.lsan.renue.autocomplete.io;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import ru.lsan.renue.autocomplete.handler.comparators.DigitsComparator;
import ru.lsan.renue.autocomplete.handler.comparators.LineComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.lsan.renue.autocomplete.utils.Utils.startsWithIgnoreCase;

public class CSVSearch {

    private static Comparator<String> comparator;
    private static String offset = "";

    public static List<String> search(String path, int nCol, String prefix){
        setComparator(path, nCol);
        List<String> list = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            list = stream
                    .map(x -> Splitter.on(",").split(x))
                    .map(Lists::newArrayList)
                    .filter(x -> startsWithIgnoreCase(x.get(nCol), offset+prefix))
                    .sorted((o1, o2) -> comparator.compare(o1.get(nCol), o2.get(nCol)))
                    .map(x -> x.get(nCol) + "[" + Joiner.on(",").join(x) + "]")
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Невозможно найти файл: "+path);
        }
        return list;
    }

    private static void setComparator(String path,int nCol) {
        try {
            Scanner sc = new Scanner(new File(path));
            String firstLine = sc.nextLine();
            String[] values = firstLine.split(",");
            String columnValue = values[nCol];
            byte[] columnBytes = columnValue.getBytes();
            if (Character.isDigit(columnBytes[0]) || Character.isDigit(columnBytes[1])) { //один символ м.б. знаковым
                comparator = new DigitsComparator();
                offset = "";
            } else {
                comparator = new LineComparator();
                offset = "\"";
            }
        } catch (FileNotFoundException ignored) {
        }
    }

}

