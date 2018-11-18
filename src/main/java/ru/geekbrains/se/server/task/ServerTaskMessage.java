package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.entity.User;
import ru.geekbrains.se.server.impl.FileServiceImpl;
import ru.geekbrains.se.server.impl.LogServiceImpl;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.utils.FileService;
import ru.geekbrains.se.server.utils.LogService;

import java.net.Socket;

public class ServerTaskMessage extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final Socket socket;

    @NotNull
    private final FileService fileService;

    @NotNull
    private final LogService logService;

    ServerTaskMessage(Server server, @NotNull final Socket socket, @NotNull final String message) {
        super(server);
        this.message = message;
        this.socket = socket;
        fileService = new FileServiceImpl();
        logService = new LogServiceImpl();
    }

    @Override
    @SneakyThrows
    public void run() {
        User userFrom = null;
        User userTo = null;

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketMessage packetMessage = objectMapper.readValue(message, PacketMessage.class);
        packetMessage.setRequest(false);

        fileService.addToFile(packetMessage);

        for (final Connection connection : server.connections()) {
            boolean isCurrentUserToSend = connection.getCurrentUser() != null
                    && connection.getCurrentUser().getLogin() != null
                    && connection.getCurrentUser().getLogin().equals(packetMessage.getLogin());
            if (isCurrentUserToSend) {
                userFrom = connection.getCurrentUser();
                connection.send(objectMapper.writeValueAsString(packetMessage));
            }
            if (!isCurrentUserToSend && connection.getSocket().equals(socket)) {
                userTo = connection.getCurrentUser();
                connection.send(objectMapper.writeValueAsString(packetMessage));
            }
        }

        logService.userSendedMsg(userFrom, userTo, message);
    }
}
