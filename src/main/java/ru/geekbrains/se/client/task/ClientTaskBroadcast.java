package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.model.PacketBroadcast;

import java.util.Scanner;

public class ClientTaskBroadcast extends AbstractClientTask {
    ClientTaskBroadcast(Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("Enter message for broadcast:");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();

        @NotNull ObjectMapper objectMapper = new ObjectMapper();
        @NotNull PacketBroadcast packetBroadcast = new PacketBroadcast();
        packetBroadcast.setMessage(message);

        client.broadcast(objectMapper.writeValueAsString(packetBroadcast));
        client.run(new ClientTaskMessageInput(client));
    }
}
