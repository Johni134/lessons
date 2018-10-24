package ru.geekbrains.se.config;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

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
    public final static String CMD_CHANGE_LOGIN = "chlogin";

    @NotNull
    public final static Integer KICKING_TIMER_SECONDS = 120;

    @NotNull
    public final static String KICKING_MSG = "Goodbye!";

    @NotNull
    private Integer port = 8080;

    @NotNull
    private String host = "localhost";

    public static boolean checkFileNotExists(final String filename, final boolean createIfNotExists) {
        final File file = new File(filename);
        if (!file.exists()) {
            if (!createIfNotExists) return true;
            try {
                file.getParentFile().mkdirs();
                if (!file.createNewFile()) return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }
}
