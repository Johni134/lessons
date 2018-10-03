package ru.geekbrains.se.server.impl;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.service.ConnectionServiceBean;
import ru.geekbrains.se.server.task.AbstractServerTask;
import ru.geekbrains.se.server.task.ServerTaskConnection;
import ru.geekbrains.se.server.task.ServerTaskMessageInput;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Getter
public class ChatServerRunner implements Server {

    private final ChatConfig config;

    private final ExecutorService executorService;

    private final ConnectionService connectionService;

    private ServerSocket serverSocket;

    ChatServerRunner(final ChatConfig config, final ExecutorService executorService) {
        this.connectionService = new ConnectionServiceBean(this);
        this.config = config;
        this.executorService = executorService;
    }

    @Override
    public ExecutorService getExecutor() {
        return executorService;
    }

    @Override
    public List<Connection> connections() {
        return connectionService.connections();
    }

    @Override
    public String getIdBySocket(Socket socket) {
        Connection connection = connectionService.get(socket);
        if (connection == null) return "server";
        return connection.getId();
    }

    @Override
    public void run(AbstractServerTask task) {
        executorService.submit(task);
    }

    @Override
    @SneakyThrows
    public void run() {
        serverSocket = new ServerSocket(config.getPort());
        run(new ServerTaskConnection(this));
        run(new ServerTaskMessageInput(this));
    }

    @Override
    public void add(Socket socket) {
        connectionService.add(socket);
    }

    @Override
    public void remove(Socket socket) {
        connectionService.remove(socket);
    }

    @Override
    @SneakyThrows
    public void exit() {
        connectionService.clear();
        serverSocket.close();
        System.out.println("Server is closed");
        System.exit(0);
    }
}
