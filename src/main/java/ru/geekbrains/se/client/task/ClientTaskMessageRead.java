package ru.geekbrains.se.client.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.se.client.Client;
import ru.geekbrains.se.client.impl.FileServiceImpl;
import ru.geekbrains.se.client.utils.FileService;
import ru.geekbrains.se.model.PacketMessage;

import java.io.IOException;

public class ClientTaskMessageRead extends AbstractClientTask {

    public ClientTaskMessageRead(Client client) {
        super(client);
    }

    @Override
    public void run() {
        try {
            @NotNull final String message = client.getIn().readUTF();
            try {
                @NotNull ObjectMapper objectMapper = new ObjectMapper();
                @NotNull PacketMessage packetMessage = objectMapper.readValue(message, PacketMessage.class);
                System.out.println("*** " + packetMessage.getMessage() + " ***");

                FileService fileService = new FileServiceImpl();
                fileService.writeToFile(packetMessage.getMessage());
            } catch (IOException e) {
                System.out.println("Wrong data, send message to your administrator!");
            }
            client.run(new ClientTaskMessageRead(client));
        } catch (IOException e) {
            e.printStackTrace();
            client.exit();
        }
    }
}
