package ru.geekbrains.se.server.impl;

import ru.geekbrains.se.config.ChatConfig;
import ru.geekbrains.se.model.Packet;
import ru.geekbrains.se.model.PacketBroadcast;
import ru.geekbrains.se.model.PacketMessage;
import ru.geekbrains.se.server.utils.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    private final String filename = "log\\serverLog.txt";
    private static final int MAX_SIZE_TO_USER = 100;


    @Override
    public boolean addToFile(final Packet packet) {
        if (ChatConfig.checkFileNotExists(filename, true)) return false;
        try {
            final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename, true));
            out.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Packet> readFromFileToUser(final String login) {
        List<Packet> arrayList = new ArrayList<>();
        Packet packet;
        ObjectInputStream in;
        if (ChatConfig.checkFileNotExists(filename, false)) return arrayList;
        try {
            final FileInputStream fileInputStream = new FileInputStream(filename);
            try {
                while (fileInputStream.available() > 0) {
                    in = new ObjectInputStream(fileInputStream);
                    packet = (Packet) in.readObject();
                    if ((packet instanceof PacketMessage && ((PacketMessage) packet).getLogin().equals(login)) || packet instanceof PacketBroadcast) {
                        arrayList.add(packet);
                    }
                }
                fileInputStream.close();
            } catch (EOFException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = arrayList.size();
        return (size > MAX_SIZE_TO_USER ? arrayList.subList(size - MAX_SIZE_TO_USER - 1, size - 1) : arrayList);
    }
}
