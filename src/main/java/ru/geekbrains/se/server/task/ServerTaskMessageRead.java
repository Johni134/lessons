package ru.geekbrains.se.server.task;

import ru.geekbrains.se.server.Server;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerTaskMessageRead extends AbstractServerTask {

    private Socket socket;

    ServerTaskMessageRead(final Server server, final Socket socket) {
        super(server);
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("in.readUTF()");
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final String message = in.readUTF();
            final String messageWithId = server.getIdBySocket(socket) + ":" + message;
            System.out.println(messageWithId);

            server.run(new ServerTaskMessageRead(server, socket));
            server.run(new ServerTaskMessageBroadcast(server, messageWithId));
        } catch (Exception e) {
            server.remove(socket);
        }
    }
}
