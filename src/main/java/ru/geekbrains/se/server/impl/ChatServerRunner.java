package ru.geekbrains.se.server.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.se.api.ConnectionService;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.server.Server;
import ru.geekbrains.se.server.model.Connection;
import ru.geekbrains.se.server.task.AbstractServerTask;
import ru.geekbrains.se.server.task.ServerTaskConnection;
import ru.geekbrains.se.server.task.ServerTaskMessageInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@ApplicationScoped
@NoArgsConstructor
public class ChatServerRunner implements Server {

    @Inject
    private ChatConfig config;

    @Inject
    private UserService userService;

    @Inject
    private ConnectionService connectionService;

    @NotNull
    private final ExecutorService executorService;

    @Nullable
    private ServerSocket serverSocket;

    {
        this.executorService = Executors.newCachedThreadPool();
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
    public Connection getConnectionBySocket(Socket socket) {
        return connectionService.get(socket);
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
    @SneakyThrows
    public void remove(Socket socket) {
        connectionService.remove(socket);
        socket.close();
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
