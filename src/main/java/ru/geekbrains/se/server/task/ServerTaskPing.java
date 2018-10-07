package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

public class ServerTaskPing extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final Socket socket;

    ServerTaskPing(Server server, @NotNull Socket socket, @NotNull String message) {
        super(server);
        this.message = message;
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {

        @NotNull ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketMessage packet = objectMapper.readValue(message, PacketMessage.class);
        packet.setMessage("PONG!");

        for (final Connection connection : server.connections()) {
            if (connection.getSocket().equals(socket)) {
                connection.send(objectMapper.writeValueAsString(packet));
            }
        }

    }
}
