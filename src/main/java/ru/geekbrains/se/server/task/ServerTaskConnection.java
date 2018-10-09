package ru.geekbrains.se.server.task;

import lombok.SneakyThrows;
import ru.geekbrains.se.server.Server;

import java.net.Socket;

public class ServerTaskConnection extends AbstractServerTask {
    public ServerTaskConnection(final Server server) {
        super(server);
    }

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("getServerSocket().accept()");
        final Socket socket = server.getServerSocket().accept();
        server.run(new ServerTaskConnection(server));
        server.run(new ServerTaskKickUnauthorized(server, socket));
        server.run(new ServerTaskMessageRead(server, socket));
        server.add(socket);
    }
}
