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
        ArrayList<Packet> arrayList = new ArrayList<>();
        if (ChatConfig.checkFileNotExists(filename, false)) return arrayList;
        try {
            final FileInputStream fileInputStream = new FileInputStream(filename);
            final ObjectInputStream in = new ObjectInputStream(fileInputStream);
            try {
                while (true) {
                    Packet packet = (Packet) in.readObject();
                    if ((packet instanceof PacketMessage && ((PacketMessage) packet).getLogin().equals(login)) || packet instanceof PacketBroadcast) {
                        arrayList.add(packet);
                    }
                }
            } catch (EOFException e) {
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = arrayList.size();
        return (size > 100 ? arrayList.subList(size - 101, size - 1) : arrayList);
    }
}
