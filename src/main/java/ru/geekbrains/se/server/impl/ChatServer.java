package ru.geekbrains.se.server.impl;

import lombok.Getter;
import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ChatServer implements ChatApp {

    private final ChatConfig config;

    private final ExecutorService executorService;

    private final Server server;

    public ChatServer() {
        config = new ChatConfig();
        executorService = Executors.newCachedThreadPool();
        server = new ChatServerRunner(config, executorService);
    }

    public static void main(String[] args) {

        final ChatServer server = new ChatServer();
        server.getConfig().setPort(9090);
        server.run();

    }

    @Override
    public ExecutorService getExecutor() {
        return executorService;
    }

    @Override
    public ChatConfig getConfig() {
        return config;
    }

    @Override
    public void run() {
        server.run();
    }
}
