package ru.geekbrains.se.server.utils;

import ru.geekbrains.se.server.entity.User;

public interface LogService {

    void serverStarted();

    void userConnected(User user);

    void userSendedMsg(User userFrom, User userTo, String msg);

    void userBroadcastMsg(User user, String msg);
}
