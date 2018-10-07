package ru.geekbrains.se.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketRegistry extends Packet {

    @Nullable
    private String login;
    @Nullable
    private String password;

    {
        setType(PacketType.REGISTRY);
    }

}
