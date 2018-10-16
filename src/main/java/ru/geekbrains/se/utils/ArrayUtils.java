package ru.geekbrains.se.utils;

import java.util.ArrayList;

public interface ArrayUtils<T> {

    void swapElements(int firstElement, int secondElement);

    T[] getArray();

    ArrayList<T> getArrayList();
}
