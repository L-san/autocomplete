package ru.lsan.renue.autocomplete.loader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileYandexDiskLoader {

    public static void getFile(String link, String outputFile) throws IOException {
        String baseURL = "https://cloud-api.yandex.net/v1/disk/public/resources/download?";
        String finalURL = baseURL + "public_key=" + link;
        URL url = new URL(finalURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder downloadURLBuffer = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            downloadURLBuffer.append(inputLine);
        }
        in.close();
        con.disconnect();

        File file = new File(outputFile);
        Path path = file.toPath();

        URL downloadURL = new URL(downloadURLBuffer.toString().split("\"")[3]);
        HttpURLConnection connection = (HttpURLConnection) downloadURL.openConnection();
        connection.setRequestMethod("GET");
        InputStream input = connection.getInputStream();
        Files.copy(input, path);
        connection.disconnect();
    }

}
