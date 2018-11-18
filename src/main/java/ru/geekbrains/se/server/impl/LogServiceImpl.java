package ru.geekbrains.se.server.impl;

import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.entity.User;
import ru.geekbrains.se.server.utils.LogService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogServiceImpl implements LogService {

    private String getFileName() {
        return "log\\serverLog-" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".txt";
    }

    private void addToFile(String strToFile) {
        try {
            final String filename = getFileName();
            if (ChatConfig.checkFileNotExists(filename, true)) return;
            final BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append(":").append(strToFile);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void serverStarted() {
        addToFile("Server started");
    }

    @Override
    public void userConnected(final User user) {
        addToFile("User " + user.getLogin() + " connected!");
    }

    @Override
    public void userSendedMsg(final User userFrom, final User userTo, final String msg) {
        String userFromLogin = "";
        String userToLogin = "";
        if (userFrom != null) userFromLogin = userFrom.getLogin();
        if (userTo != null) userToLogin = userTo.getLogin();
        addToFile("Msg from " + userFromLogin + " to " + userToLogin + ": " + msg);
    }

    @Override
    public void userBroadcastMsg(final User user, final String msg) {
        String userLogin = "";
        if (user != null) userLogin = user.getLogin();
        addToFile("Broadcast from " + userLogin + ": " + msg);
    }
}
