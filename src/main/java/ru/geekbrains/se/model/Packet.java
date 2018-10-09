package ru.geekbrains.se.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Packet {

    private String id = UUID.randomUUID().toString();

    private PacketType type = PacketType.NONE;

    private boolean isRequest = true;

    private Long timestamp = System.currentTimeMillis();
}
