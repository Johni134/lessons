package ru.geekbrains.se.client.task;

import ru.geekbrains.se.client.Client;

import java.util.Scanner;

import static ru.geekbrains.se.config.ChatConfig.CMD_EXIT;

public class ClientTaskMessageInput extends AbstractClientTask {

    public ClientTaskMessageInput(final Client client) {
        super(client);
    }

    @Override
    public void run() {
        System.out.println("Enter cmd (message or exit)");
        final Scanner in = new Scanner(System.in);
        final String message = in.nextLine();

        if (CMD_EXIT.equals(message)) {
            client.exit();
            return;
        }

        client.broadcast(message);
        System.out.println();
        client.run(new ClientTaskMessageInput(client));
    }
}
