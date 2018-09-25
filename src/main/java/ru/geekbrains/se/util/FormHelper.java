package ru.geekbrains.se.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormHelper {

    public static String getFormattedDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date.format(formatter);
    }

}
