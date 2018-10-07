package ru.geekbrains.se.server.impl;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.api.UserService;
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

    @NotNull
    private final ChatConfig config;

    @NotNull
    private final ExecutorService executorService;

    @NotNull
    private final ConnectionService connectionService;

    @NotNull
    private final UserService userService;

    @Nullable
    private ServerSocket serverSocket;

    ChatServerRunner(@NotNull final ChatConfig config, @NotNull final ExecutorService executorService, @NotNull final UserService userService) {
        this.connectionService = new ConnectionServiceBean(this);
        this.config = config;
        this.executorService = executorService;
        this.userService = userService;
    }

    @Override
    public ExecutorService getExecutor() {
        return executorService;
    }

    @Override
    public List<Connection> connections() {
        return connectionService.connections();
    }

    @NotNull
    @Override
    public UserService getUserService() {
        return userService;
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
        if (serverSocket != null)
            serverSocket.close();
        System.out.println("Server is closed");
        System.exit(0);
    }
}
