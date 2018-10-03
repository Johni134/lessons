package ru.geekbrains.se.client.task;

import ru.geekbrains.se.client.Client;

import java.io.IOException;

public class ClientTaskMessageSend extends AbstractClientTask {

    private String message;

    public ClientTaskMessageSend(Client client, String message) {
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
