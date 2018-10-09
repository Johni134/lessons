package ru.geekbrains.se.api;

import ru.geekbrains.se.config.ChatConfig;

public interface ChatApp extends Runnable {

    ChatConfig getConfig();

    void run();
}
