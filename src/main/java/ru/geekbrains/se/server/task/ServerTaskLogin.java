package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.model.PacketLogin;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

public class ServerTaskLogin extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final UserService userService;

    @NotNull
    private final Socket socket;

    ServerTaskLogin(Server server, @NotNull Socket socket, @NotNull String message) {
        super(server);
        this.message = message;
        this.userService = server.getUserService();
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packetLogin = objectMapper.readValue(message, PacketLogin.class);
        @NotNull PacketMessage packetMessage = new PacketMessage();

        boolean success = userService.check(packetLogin.getLogin(), packetLogin.getPassword());
        if (success) {
            packetMessage.setMessage("Success!");
        } else {
            packetMessage.setMessage("Failed!");
        }
        for (final Connection connection : server.connections()) {
            if (connection.getSocket().equals(socket)) {
                connection.setCurrentUser(userService.findByUser(packetLogin.getLogin()));
                connection.send(objectMapper.writeValueAsString(packetMessage));
            }

        }

    }
}
