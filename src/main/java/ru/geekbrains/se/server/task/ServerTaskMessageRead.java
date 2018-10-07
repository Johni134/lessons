package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.Packet;
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
            @NotNull final DataInputStream in = new DataInputStream(socket.getInputStream());
            @NotNull final String message = in.readUTF();
            System.out.println(message);
            //final String messageWithId = server.getIdBySocket(socket) + ":" + message;
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final Packet packet = objectMapper.readValue(message, Packet.class);

            switch (packet.getType()) {
                case PING:
                    server.run(new ServerTaskPing(server, socket, message));
                    break;

                case REGISTRY:
                    server.run(new ServerTaskRegistry(server, socket, message));
                    break;

                case LOGIN:
                    server.run(new ServerTaskLogin(server, socket, message));
                    break;

                case MESSAGE:
                    server.run(new ServerTaskMessage(server, socket, message));
                    break;

                case BROADCAST:
                    server.run(new ServerTaskMessageBroadcast(server, message));
                    break;
            }
            server.run(new ServerTaskMessageRead(server, socket));

        } catch (Exception e) {
            server.remove(socket);
        }
    }
}
