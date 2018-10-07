package ru.geekbrains.se;

import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.client.impl.ChatClient;
import ru.geekbrains.se.server.impl.ChatServer;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        getApp(args).run();
    }

    private static ChatApp getApp(String[] args) {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize();

        if (args == null || args.length == 0) return CDI.current().select(ChatClient.class).get();
        if (args.length == 1 && "server".equals(args[0])) return CDI.current().select(ChatServer.class).get();
        return CDI.current().select(ChatClient.class).get();
    }
}
