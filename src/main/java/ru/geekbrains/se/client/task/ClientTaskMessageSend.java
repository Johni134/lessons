package ru.geekbrains.se.client.task;

import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;

import java.io.IOException;

public class ClientTaskMessageSend extends AbstractClientTask {

    @NotNull
    private String message;

    public ClientTaskMessageSend(Client client, @NotNull String message) {
        super(client);
        this.message = message;
    }

    @Override
    public void run() {
        try {
            client.getOut().writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            client.exit();
        }
    }
}
