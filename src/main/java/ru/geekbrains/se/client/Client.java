package ru.geekbrains.se.client;

import ru.geekbrains.se.client.task.AbstractClientTask;
import ru.geekbrains.se.config.ChatConfig;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public interface Client extends Runnable {

    ExecutorService getExecutor();

    ChatConfig getConfig();

    Socket getSocket();

    DataInputStream getIn();

    DataOutputStream getOut();

    void run(AbstractClientTask task);

    void run();

    void broadcast(String message);

    void exit();


}
