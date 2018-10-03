package ru.geekbrains.se.server.model;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.geekbrains.se.server.Server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

@Getter
public class Connection {

    private final String id = UUID.randomUUID().toString();

    private final Server server;

    private final Socket socket;

    public Connection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @SneakyThrows
    public void send(final String message) {
        final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF(message);
    }
}
