package ru.geekbrains.se.client.impl;

import lombok.Getter;
import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.config.ChatConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Getter
public class ChatClient implements ChatApp {

    @Inject
    private ChatConfig config;

    @Inject
    private Client client;

    public static void main(String[] args) {
        final ChatClient chatClient = new ChatClient();
        chatClient.getConfig().setPort(9090);
        chatClient.run();
    }

    @Override
    public void run() {
        client.run();
    }
}
