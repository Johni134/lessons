package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.model.PacketChangeLogin;

import java.util.Scanner;

public class ClientTaskChangeLogin extends AbstractClientTask {

    ClientTaskChangeLogin(final Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Enter new login:");
        @NotNull final String login = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketChangeLogin packet = new PacketChangeLogin();
        packet.setLogin(login);

        client.broadcast(objectMapper.writeValueAsString(packet));
        client.run(new ClientTaskMessageInput(client));
    }
}
