package ru.geekbrains.se.server.task;

import ru.geekbrains.se.server.Server;

public abstract class AbstractServerTask extends Thread {

    protected final Server server;

    AbstractServerTask(Server server) {
        this.server = server;
    }
}
