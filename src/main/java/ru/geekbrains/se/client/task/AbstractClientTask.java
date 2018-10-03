package ru.geekbrains.se.client.task;

import ru.geekbrains.se.client.Client;

public abstract class AbstractClientTask extends Thread {

    final Client client;

    AbstractClientTask(Client client) {
        this.client = client;
    }
}
