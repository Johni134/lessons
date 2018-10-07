package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

public class ServerTaskMessage extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final Socket socket;

    ServerTaskMessage(Server server, @NotNull final Socket socket, @NotNull final String message) {
        super(server);
        this.message = message;
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketMessage packetMessage = objectMapper.readValue(message, PacketMessage.class);
        for (final Connection connection : server.connections()) {
            boolean isCurrentUserToSend = connection.getCurrentUser() != null
                    && connection.getCurrentUser().getLogin() != null
                    && connection.getCurrentUser().getLogin().equals(packetMessage.getLogin());
            if (isCurrentUserToSend) connection.send(objectMapper.writeValueAsString(packetMessage));
            if (!isCurrentUserToSend && connection.getSocket().equals(socket))
                connection.send(objectMapper.writeValueAsString(packetMessage));
        }

    }
}
