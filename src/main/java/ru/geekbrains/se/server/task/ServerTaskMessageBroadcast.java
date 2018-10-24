package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketBroadcast;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.impl.FileServiceImpl;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.utils.FileService;


public class ServerTaskMessageBroadcast extends AbstractServerTask {

    private final String message;

    @NotNull
    private final FileService fileService;

    ServerTaskMessageBroadcast(Server server, String message) {
        super(server);
        this.message = message;
        fileService = new FileServiceImpl();
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketBroadcast packetMessage = objectMapper.readValue(message, PacketBroadcast.class);
        fileService.addToFile(packetMessage);
        for (final Connection connection : server.connections()) {
            connection.send(objectMapper.writeValueAsString(packetMessage));
        }
    }
}
