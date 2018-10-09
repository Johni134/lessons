package ru.geekbrains.se.server;

import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.task.AbstractServerTask;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

public interface Server extends Runnable {

    ExecutorService getExecutor();

    ChatConfig getConfig();

    ServerSocket getServerSocket();

    List<Connection> connections();

    UserService getUserService();

    String getIdBySocket(Socket socket);

    Connection getConnectionBySocket(Socket socket);

    void run(AbstractServerTask task);

    void run();

    void add(Socket socket);

    void remove(Socket socket);

    void exit();
}
