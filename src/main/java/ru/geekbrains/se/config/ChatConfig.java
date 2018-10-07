package ru.geekbrains.se.config;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class ChatConfig {

    @NotNull
    public final static String CMD_EXIT = "exit";

    @NotNull
    public final static String CMD_PING = "ping";

    @NotNull
    public final static String CMD_LOGIN = "login";

    @NotNull
    public final static String CMD_BROADCAST = "broadcast";

    @NotNull
    public final static String CMD_REGISTRY = "registry";

    @NotNull
    public final static String CMD_MESSAGE = "msg";

    @NotNull
    private Integer port = 8080;

    @NotNull
    private String host = "localhost";

}
