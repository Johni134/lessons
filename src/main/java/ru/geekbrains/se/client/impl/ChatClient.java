package ru.geekbrains.se.client.impl;

import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.config.ChatConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient implements ChatApp {

    private final ChatConfig config;

    private final ExecutorService executorService;

    private final Client client;

    public ChatClient() {
        config = new ChatConfig();
        executorService = Executors.newCachedThreadPool();
        client = new ChatClientRunner(config, executorService);
    }

    public static void main(String[] args) {
        final ChatClient chatClient = new ChatClient();
        chatClient.getConfig().setPort(9090);
        chatClient.run();
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
        client.run();
    }
}
