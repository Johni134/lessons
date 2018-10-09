package ru.geekbrains.se.server.impl;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Getter
@ApplicationScoped
public class ChatServer implements ChatApp {

    @Inject
    private ChatConfig config;

    @Inject
    private UserService userServiceBean;

    @Inject
    private Server server;

    public static void main(String[] args) {

        final ChatServer server = new ChatServer();
        server.getConfig().setPort(9090);
        server.run();

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
