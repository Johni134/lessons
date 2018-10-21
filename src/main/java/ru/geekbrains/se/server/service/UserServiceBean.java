package ru.geekbrains.se.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mindrot.jbcrypt.BCrypt;
import ru.geekbrains.se.api.UserService;
import ru.geekbrains.se.server.entity.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor
@ApplicationScoped
public class UserServiceBean implements UserService {

    private UserEntityService userEntityService;

    {
        try {
            userEntityService = new UserEntityService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void init() {

        if (userEntityService.countAll() == 0) {
            registry("admin", "admin");
            registry("test", "test");
        }
    }

    @Override
    public User findByUser(String login) {
        if (login == null || login.isEmpty()) return null;
        return userEntityService.findByLogin(login);
    }

    @Override
    public boolean check(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null || user.getPassword() == null) return false;
        return BCrypt.checkpw(password, user.getPassword());
    }

    @Override
    public boolean registry(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (exists(login)) return false;
        @NotNull final User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userEntityService.insert(user);
        return true;
    }

    @Override
    public boolean exists(String login) {
        if (login == null || login.isEmpty()) return false;
        return (userEntityService.findByLogin(login) != null);
    }

    @Override
    public boolean setLogin(String login, String newLogin) {
        if (login == null || login.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null) return false;
        if (exists(newLogin)) return false;
        user.setLogin(newLogin);
        userEntityService.updateLogin(user);
        return true;
    }

    @Override
    public boolean setPassword(String login, String passwordOld, String passwordNew) {
        if (!check(login, passwordOld)) return false;
        if (passwordNew == null || passwordNew.isEmpty()) return false;
        @Nullable final User user = findByUser(login);
        if (user == null) return false;
        user.setPassword(BCrypt.hashpw(passwordNew, BCrypt.gensalt()));
        userEntityService.updatePassword(user);
        return true;
    }
}
