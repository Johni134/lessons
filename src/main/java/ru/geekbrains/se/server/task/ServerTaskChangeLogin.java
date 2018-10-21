package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.model.PacketChangeLogin;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.entity.User;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

public class ServerTaskChangeLogin extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final UserService userService;

    @NotNull
    private final Socket socket;

    ServerTaskChangeLogin(Server server, @NotNull Socket socket, @NotNull String message) {
        super(server);
        this.message = message;
        this.userService = server.getUserService();
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {

        User currentUser = null;
        Connection currentConnection = null;
        String messageToConnection;

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketChangeLogin packetChangeLogin = objectMapper.readValue(message, PacketChangeLogin.class);
        @NotNull PacketMessage packetMessage = new PacketMessage();

        final String newLogin = packetChangeLogin.getLogin();

        for (final Connection connection : server.connections()) {
            if (connection.getSocket().equals(socket)) {
                currentConnection = connection;
                currentUser = connection.getCurrentUser();
            }
        }
        if (currentUser == null) {
            messageToConnection = "Login before changing login name!";
        } else if (currentUser.getLogin() != null && currentUser.getLogin().equals(newLogin)) {
            messageToConnection = "New login is the same, that old one";
        } else {
            if (userService.setLogin(currentUser.getLogin(), newLogin)) {
                messageToConnection = "Success!";
            } else {
                messageToConnection = "Failed!";
            }
        }

        packetMessage.setMessage(messageToConnection);

        if (currentConnection != null) currentConnection.send(objectMapper.writeValueAsString(packetMessage));
    }
}
