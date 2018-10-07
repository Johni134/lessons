package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.model.PacketLogin;

import java.util.Scanner;

public class ClientTaskLogin extends AbstractClientTask {

    ClientTaskLogin(final Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Enter login:");
        @NotNull final String login = in.nextLine();

        System.out.println("Enter password:");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packet = new PacketLogin();
        packet.setLogin(login);
        packet.setPassword(password);

        client.broadcast(objectMapper.writeValueAsString(packet));
        client.run(new ClientTaskMessageInput(client));
    }
}
