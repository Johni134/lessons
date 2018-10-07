package ru.geekbrains.se.server.impl;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.service.UserServiceBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ChatServer implements ChatApp {

    @NotNull
    private final ChatConfig config;

    @NotNull
    private final ExecutorService executorService;

    @NotNull
    private final Server server;

    @NotNull
    private final UserService userServiceBean;

    private ChatServer() {
        config = new ChatConfig();
        executorService = Executors.newCachedThreadPool();
        userServiceBean = new UserServiceBean();
        server = new ChatServerRunner(config, executorService, userServiceBean);
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

    @NotNull
    @Override
    public ChatConfig getConfig() {
        return config;
    }

    @Override
    public void run() {
        server.run();
    }
}
