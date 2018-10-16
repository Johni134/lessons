package ru.geekbrains.se.box;

import ru.geekbrains.se.model.Fruit;

public interface Box<T extends Fruit> {

    Number getWeight();

    boolean compare(Box<?> box);

    void add(T fruit);

    void pour(Box<T> box);
}
