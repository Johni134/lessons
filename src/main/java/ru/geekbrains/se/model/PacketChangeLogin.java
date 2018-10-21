package ru.geekbrains.se.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketChangeLogin extends Packet {

    private String login;

    {
        setType(PacketType.CHANGE_LOGIN);
    }
}
