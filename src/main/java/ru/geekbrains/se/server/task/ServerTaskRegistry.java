package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.model.PacketRegistry;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

public class ServerTaskRegistry extends AbstractServerTask {

    @NotNull
    private final String message;

    @NotNull
    private final Socket socket;

    @NotNull
    private UserService userService;

    ServerTaskRegistry(Server server, @NotNull Socket socket, @NotNull String message) {
        super(server);
        this.message = message;
        this.userService = server.getUserService();
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketRegistry packet = objectMapper.readValue(message, PacketRegistry.class);
        @NotNull PacketMessage packetMessage = new PacketMessage();

        boolean success = userService.registry(packet.getLogin(), packet.getPassword());
        if (success) {
            packetMessage.setMessage("Registry success!");
        } else {
            packetMessage.setMessage("Registry failed!");
        }
        for (final Connection connection : server.connections()) {
            if (connection.getSocket().equals(socket))
                connection.send(objectMapper.writeValueAsString(packetMessage));
        }
    }
}
