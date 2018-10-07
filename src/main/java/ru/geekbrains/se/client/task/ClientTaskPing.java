package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.model.PacketPing;

public class ClientTaskPing extends AbstractClientTask {

    ClientTaskPing(Client client) {
        super(client);
    }

    @Override
    @SneakyThrows
    public void run() {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketPing packet = new PacketPing();

        client.broadcast(objectMapper.writeValueAsString(packet));
        client.run(new ClientTaskMessageInput(client));
    }
}
