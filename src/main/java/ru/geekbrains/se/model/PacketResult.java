package ru.geekbrains.se.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketResult extends Packet {

    private Boolean success = true;

    {
        setType(PacketType.RESULT);
    }

    public PacketResult(Boolean success) {
        this.success = success;
    }
}
