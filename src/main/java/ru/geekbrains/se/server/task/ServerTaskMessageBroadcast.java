package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketBroadcast;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;


public class ServerTaskMessageBroadcast extends AbstractServerTask {

    private String message;

    ServerTaskMessageBroadcast(Server server, String message) {
        super(server);
        this.message = message;
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketBroadcast packetMessage = objectMapper.readValue(message, PacketBroadcast.class);
        for (final Connection connection : server.connections()) {
            connection.send(objectMapper.writeValueAsString(packetMessage));
        }
    }
}
