package ru.geekbrains.se.server.service;

import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionServiceBean implements ConnectionService {

    @NotNull
    private final Server server;

    @NotNull
    private final List<Connection> connections = new ArrayList<>();

    @NotNull
    public ConnectionServiceBean(@NotNull Server server) {
        this.server = server;
    }

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

//    @Override
//    public void setLogin(Socket socket, String login) {
//        User currentUser = server.getUserService().findByUser(login);
//        if (currentUser == null) return;
//        if (socket == null) return;
//        for (final Connection connection : connections) {
//            if (connection.getSocket().equals(socket))
//                connection.setCurrentUser(currentUser);
//        }
//    }

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
