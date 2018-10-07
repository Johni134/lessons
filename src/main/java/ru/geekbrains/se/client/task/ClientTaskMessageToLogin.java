package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.model.PacketMessage;

import java.util.Scanner;

public class ClientTaskMessageToLogin extends AbstractClientTask {

    ClientTaskMessageToLogin(Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Enter login to send message:");
        @NotNull final String login = in.nextLine();

        System.out.println("Enter message:");
        @NotNull final String message = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketMessage packetMessage = new PacketMessage();
        packetMessage.setLogin(login);
        packetMessage.setMessage(message);

        client.broadcast(objectMapper.writeValueAsString(packetMessage));
        client.run(new ClientTaskMessageInput(client));
    }

}
