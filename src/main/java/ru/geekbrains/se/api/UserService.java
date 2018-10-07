package ru.geekbrains.se.api;

import org.jetbrains.annotations.Nullable;
import ru.geekbrains.se.model.User;

public interface UserService {

    @Nullable
    User findByUser(@Nullable String login);

    boolean check(
            @Nullable String login,
            @Nullable String password
    );

    boolean registry(
            @Nullable String login,
            @Nullable String password
    );

    boolean exists(@Nullable String login);

    boolean setNick(
            @Nullable String login,
            @Nullable String nick
    );

    boolean setPassword(
            @Nullable String login,
            @Nullable String passwordOld,
            @Nullable String passwordNew
    );
}
