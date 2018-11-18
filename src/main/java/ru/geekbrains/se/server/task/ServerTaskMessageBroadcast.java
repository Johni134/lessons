package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketBroadcast;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.entity.User;
import ru.geekbrains.se.server.impl.FileServiceImpl;
import ru.geekbrains.se.server.impl.LogServiceImpl;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.utils.FileService;
import ru.geekbrains.se.server.utils.LogService;

import java.net.Socket;


public class ServerTaskMessageBroadcast extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final Socket socket;

    @NotNull
    private final FileService fileService;

    @NotNull
    private final LogService logService;

    ServerTaskMessageBroadcast(Server server, @NotNull final Socket socket, @NotNull final String message) {
        super(server);
        this.message = message;
        this.socket = socket;
        fileService = new FileServiceImpl();
        logService = new LogServiceImpl();
    }

    @Override
    @SneakyThrows
    public void run() {
        User currentUser = null;
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketBroadcast packetMessage = objectMapper.readValue(message, PacketBroadcast.class);
        fileService.addToFile(packetMessage);
        for (final Connection connection : server.connections()) {
            connection.send(objectMapper.writeValueAsString(packetMessage));
            if (connection.getSocket().equals(socket)) currentUser = connection.getCurrentUser();
        }
        logService.userBroadcastMsg(currentUser, message);
    }
}
