package ru.lsan.renue.autocomplete.utils;

public class Utils {

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

}
