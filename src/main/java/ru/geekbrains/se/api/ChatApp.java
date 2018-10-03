package ru.geekbrains.se.api;

import ru.geekbrains.se.config.ChatConfig;

import java.util.concurrent.ExecutorService;

public interface ChatApp extends Runnable {

    ExecutorService getExecutor();

    ChatConfig getConfig();

    void run();
}
