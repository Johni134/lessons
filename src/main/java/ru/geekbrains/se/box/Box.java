package ru.geekbrains.se.box;

import ru.geekbrains.se.model.Fruit;

import java.util.List;

public interface Box<T extends Fruit> {

    Float getWeight();

    boolean compare(Box<?> box);

    void add(T fruit);

    void addAll(List<T> fruitList);

    void move(Box<T> box);

    List<T> getFruits();
}
