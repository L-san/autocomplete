package ru.lsan.renue.autocomplete;

import ru.lsan.renue.autocomplete.handler.ProcessHandler;
import ru.lsan.renue.autocomplete.loader.CSVYandexDiskLoader;
import ru.lsan.renue.autocomplete.statemachine.StateEnum;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static StateEnum state = StateEnum.BEGIN;
    private static String pattern = "";
    private static final String QUIT_COMMAND = "!quit";

    public static void main(String[] args) throws IOException {
        String filePath = "airports.csv";
        CSVYandexDiskLoader.getFile("https://disk.yandex.ru/i/g1riHSgEntfLYQ", filePath); //cкачать файл

        Scanner scanner = new Scanner(System.in);
        int nCol = 0;
        try {
            nCol = Integer.parseInt(args[0]) - 1;
            if (nCol < 0) throw new Exception("Некорректный номер столбца");
        } catch (Exception e) {
            System.out.println("Некорректный номер столбца");
            System.exit(1);
        }

        while (state != StateEnum.END) {
            switch (state) {
                case BEGIN:
                    System.out.println("Введите строку");
                    pattern = scanner.nextLine();
                    if (pattern.equals(QUIT_COMMAND)) {
                        state = StateEnum.END;
                    } else {
                        state = StateEnum.PROCESS;
                    }
                    break;
                case PROCESS:
                    ProcessHandler.handle(pattern, nCol, filePath);
                    state = StateEnum.BEGIN;
                    break;
            }
        }
        scanner.close();
    }

}
