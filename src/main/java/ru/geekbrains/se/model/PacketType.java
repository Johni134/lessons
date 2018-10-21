package ru.geekbrains.se.model;

public enum PacketType {

    NONE,
    PING,
    RESULT,

    REGISTRY,
    LOGOUT,
    LOGIN,
    CHANGE_LOGIN,

    MESSAGE,
    BROADCAST

}
