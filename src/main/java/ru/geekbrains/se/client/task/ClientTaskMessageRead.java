package ru.geekbrains.se.client.task;

import ru.geekbrains.se.client.Client;

import java.io.IOException;

public class ClientTaskMessageRead extends AbstractClientTask {

    public ClientTaskMessageRead(Client client) {
        super(client);
    }

    @Override
    public void run() {
        try {
            final String message = client.getIn().readUTF();
            System.out.println("*** " + message + " ***");
            client.run(new ClientTaskMessageRead(client));
        } catch (IOException e) {
            e.printStackTrace();
            client.exit();
        }
    }
}
