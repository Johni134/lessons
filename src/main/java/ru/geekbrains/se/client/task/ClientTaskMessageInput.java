package ru.geekbrains.se.client.task;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;

import java.util.Scanner;

import static ru.geekbrains.se.config.ChatConfig.*;

public class ClientTaskMessageInput extends AbstractClientTask {

    public ClientTaskMessageInput(final Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Enter cmd (message or exit)");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();

        if (CMD_EXIT.equals(message)) {
            client.exit();
            return;
        }

        if (CMD_LOGIN.equals(message)) {
            client.run(new ClientTaskLogin(client));
            return;
        }

        if (CMD_PING.equals(message)) {
            client.run(new ClientTaskPing(client));
            return;
        }

        if (CMD_REGISTRY.equals(message)) {
            client.run(new ClientTaskRegistry(client));
            return;
        }

        if (CMD_BROADCAST.equals(message)) {
            client.run(new ClientTaskBroadcast(client));
            return;
        }

        if (CMD_MESSAGE.equals(message)) {
            client.run(new ClientTaskMessageToLogin(client));
            return;
        }

        System.out.println();
        client.run(new ClientTaskMessageInput(client));
    }
}
