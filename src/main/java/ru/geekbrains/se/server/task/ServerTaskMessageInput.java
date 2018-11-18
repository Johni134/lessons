package ru.geekbrains.se.server.task;

import ru.geekbrains.se.server.Server;

import java.net.Socket;
import java.util.Scanner;

import static ru.geekbrains.se.config.ChatConfig.CMD_EXIT;

public class ServerTaskMessageInput extends AbstractServerTask {

    public ServerTaskMessageInput(Server server) {
        super(server);
    }

    @Override
    public void run() {
        final Scanner in = new Scanner(System.in);
        final String message = in.nextLine();

        if (CMD_EXIT.equals(message)) {
            server.exit();
            return;
        }

        server.run(new ServerTaskMessageBroadcast(server, new Socket(), message));
        server.run(new ServerTaskMessageInput(server));
    }
}
