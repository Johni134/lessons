package ru.geekbrains.se.server.task;

import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;


public class ServerTaskMessageBroadcast extends AbstractServerTask {

    private String message;

    ServerTaskMessageBroadcast(Server server, String message) {
        super(server);
        this.message = message;
    }

    @Override
    public void run() {
        for (final Connection connection : server.connections()) {
            connection.send(message);
        }
    }
}
