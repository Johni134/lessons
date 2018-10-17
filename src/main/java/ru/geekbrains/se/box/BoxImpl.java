package ru.geekbrains.se.box;

import lombok.NoArgsConstructor;
import ru.geekbrains.se.model.Fruit;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BoxImpl<T extends Fruit> implements Box<T> {

    private ArrayList<T> fruitArray;

    {
        fruitArray = new ArrayList<>();
    }

    @Override
    public Float getWeight() {
        Float boxWeight = 0f;
        for (T fruit : fruitArray) {
            boxWeight += fruit.getWeight();
        }
        return boxWeight;
    }

    @Override
    public boolean compare(Box<?> box) {
        return (this.getWeight().equals(box.getWeight()));
    }

    @Override
    public void add(T fruit) {
        fruitArray.add(fruit);
    }

    @Override
    public void addAll(List<T> fruitList) {
        fruitArray.addAll(fruitList);
    }

    /**
     * Moving elements from one box to another
     *
     * @param box another box for adding elements
     */
    @Override
    public void move(Box<T> box) {
        box.addAll(fruitArray);
        fruitArray.clear();
    }

    @Override
    public List<T> getFruits() {
        return fruitArray;
    }

}
