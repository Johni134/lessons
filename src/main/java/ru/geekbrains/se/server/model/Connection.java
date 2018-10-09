package ru.geekbrains.se.server.model;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.se.model.User;
import ru.geekbrains.se.server.Server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

@Getter
public class Connection {

    @NotNull
    private final String id = UUID.randomUUID().toString();

    @NotNull
    private final Server server;

    @NotNull
    private final Socket socket;

    @Nullable
    private User currentUser;

    public Connection(@NotNull Server server, @NotNull Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void setCurrentUser(@Nullable User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isAuthorized() {
        return currentUser != null;
    }

    @SneakyThrows
    public void send(final String message) {
        @NotNull final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF(message);
    }
}
