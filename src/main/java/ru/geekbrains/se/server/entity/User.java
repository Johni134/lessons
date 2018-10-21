package ru.geekbrains.se.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String email;

    @Nullable
    private String nick;

}
