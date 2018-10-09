package ru.geekbrains.se.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public class ConnectionServiceBean implements ConnectionService {

    @Inject
    private Server server;

    @NotNull
    private final List<Connection> connections = new ArrayList<>();

    @Override
    public List<Connection> connections() {
        return connections;
    }

    @Override
    public Connection get(Socket socket) {
        if (socket == null) return null;
        for (final Connection connection : connections) {
            if (connection.getSocket().equals(socket)) return connection;
        }
        return null;
    }

    @Override
    public void add(Socket socket) {
        if (socket == null) return;
        final Connection connection = new Connection(server, socket);
        connections.add(connection);
        System.out.println("Added connection with id = " + connection.getId());
    }

    @Override
    public void remove(Socket socket) {
        if (socket == null) return;
        final Connection connection = get(socket);
        connections.remove(connection);
        System.out.println("Removed connection with id = " + connection.getId());
    }

    @Override
    public void clear() {
        connections.clear();
        System.out.println("All connections was removed");
    }
}
