package ru.geekbrains.se.client.impl;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.client.task.AbstractClientTask;
import ru.geekbrains.se.client.task.ClientTaskMessageInput;
import ru.geekbrains.se.client.task.ClientTaskMessageRead;
import ru.geekbrains.se.client.task.ClientTaskMessageSend;
import ru.geekbrains.se.config.ChatConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@ApplicationScoped
public class ChatClientRunner implements Client {

    @Inject
    private ChatConfig config;

    private final ExecutorService executorService;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    ChatClientRunner() {
        this.executorService = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        final ChatClient client = new ChatClient();
        client.run();
    }

    @Override
    public ExecutorService getExecutor() {
        return executorService;
    }

    @Override
    public void run(final AbstractClientTask task) {
        if (task == null) return;
        executorService.submit(task);
    }

    @Override
    public void run() {
        final String host = config.getHost();
        final Integer port = config.getPort();
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            run(new ClientTaskMessageRead(this));
            run(new ClientTaskMessageInput(this));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server on host " + host + " and port " + port + " not found");
        }
    }

    @Override
    public void broadcast(String message) {
        if (message == null || message.isEmpty()) return;
        run(new ClientTaskMessageSend(this, message));
    }

    @Override
    @SneakyThrows
    public void exit() {
        socket.close();
        System.out.println("Chat client disconnected...");
        System.exit(0);
    }
}
