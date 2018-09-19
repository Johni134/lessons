package ru.geekbrains.se.exception;

public class MyArrayDataException extends MyBaseException {
    private int i;
    private int j;

    public MyArrayDataException(String message, int i, int j) {
        super(message);
        this.i = i;
        this.j = j;
    }

    @Override
    public String getCustomErrorInfo() {
        return "Не смогли преобразовать значение к int в элементе с индексом [" + i + "][" + j + "]";
    }
}