package ru.geekbrains.se.server.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;

import static ru.geekbrains.se.config.ChatConfig.KICKING_MSG;
import static ru.geekbrains.se.config.ChatConfig.KICKING_TIMER_SECONDS;

public class ServerTaskKickUnauthorized extends AbstractServerTask {

    @NonNull
    private final Socket socket;

    ServerTaskKickUnauthorized(Server server, Socket socket) {
        super(server);
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {
        // waiting timer for kicking unauthorized
        Thread.sleep(KICKING_TIMER_SECONDS * 1000);

        Connection connection = server.getConnectionBySocket(socket);
        // not authorized
        if (connection != null && !connection.isAuthorized()) {
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull PacketMessage packetMessage = new PacketMessage();
            packetMessage.setMessage(KICKING_MSG);
            packetMessage.setRequest(false);
            // send goodbay msg and close socket
            connection.send(objectMapper.writeValueAsString(packetMessage));
            server.remove(socket);
        }
    }
}
