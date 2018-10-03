package ru.geekbrains.se;

import ru.geekbrains.se.api.ChatApp;
import ru.geekbrains.se.client.impl.ChatClient;
import ru.geekbrains.se.server.impl.ChatServer;

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
        if (args == null || args.length == 0) return new ChatClient();
        if (args.length == 1 && "server".equals(args[0])) return new ChatServer();
        return new ChatClient();
    }
}
