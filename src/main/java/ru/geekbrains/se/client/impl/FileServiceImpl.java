package ru.geekbrains.se.client.impl;

import ru.geekbrains.se.client.utils.FileService;
import ru.geekbrains.se.config.ChatConfig;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileServiceImpl implements FileService {

    private String getFileName() {
        return "log\\" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".txt";
    }

    @Override
    public boolean writeToFile(final String strToFile) {
        try {
            final String filename = getFileName();
            if (ChatConfig.checkFileNotExists(filename, true)) return false;
            final BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.append(strToFile);
            bw.newLine();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readFromFile() {
        try {
            final String filename = getFileName();
            if (ChatConfig.checkFileNotExists(filename, false)) return null;
            final BufferedReader br = new BufferedReader(new FileReader(filename));
            final StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null)
                stringBuilder.append(str);
            br.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
