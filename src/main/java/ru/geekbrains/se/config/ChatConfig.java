package ru.geekbrains.se.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatConfig {

    public final static String CMD_EXIT = "exit";

    private Integer port = 8080;

    private String host = "localhost";

}
