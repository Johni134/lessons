package ru.geekbrains.se.server.service;

import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionServiceBean implements ConnectionService {

    private final Server server;

    private final List<Connection> connections = new ArrayList<>();

    public ConnectionServiceBean(Server server) {
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
