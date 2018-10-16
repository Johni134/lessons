package ru.geekbrains.se.box;

import ru.geekbrains.se.model.Fruit;

public class BoxImpl<T extends Fruit> implements Box<T> {
    @Override
    public Number getWeight() {
        return null;
    }

    @Override
    public boolean compare(Box<?> box) {
        return false;
    }

    @Override
    public void add(T fruit) {

    }

    @Override
    public void pour(Box<T> box) {

    }
}
