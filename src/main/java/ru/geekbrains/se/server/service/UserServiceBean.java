package ru.geekbrains.se.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@ApplicationScoped
public class UserServiceBean implements UserService {

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        registry("admin", "admin");
        registry("test", "test");
    }

    @Override
    public User findByUser(String login) {
        if (login == null || login.isEmpty()) return null;
        return users.get(login);
    }

    @Override
    public boolean check(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

    @Override
    public boolean registry(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (exists(login)) return false;
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        users.put(login, user);
        return true;
    }

    @Override
    public boolean exists(String login) {
        if (login == null || login.isEmpty()) return false;
        return users.containsKey(login);
    }

    @Override
    public boolean setNick(String login, String nick) {
        if (login == null || login.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null) return false;
        user.setNick(nick);
        return true;
    }

    @Override
    public boolean setPassword(String login, String passwordOld, String passwordNew) {
        if (!check(login, passwordOld)) return false;
        if (passwordNew == null || passwordNew.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null) return false;
        user.setPassword(passwordNew);
        return true;
    }
}
