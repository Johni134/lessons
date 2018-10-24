package ru.geekbrains.se.server.utils;

import ru.geekbrains.se.model.Packet;

import java.util.List;

public interface FileService {

    boolean addToFile(Packet packet);

    List<Packet> readFromFileToUser(String login);
}
