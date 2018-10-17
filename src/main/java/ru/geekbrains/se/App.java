package ru.geekbrains.se;

import ru.geekbrains.se.box.Box;
import ru.geekbrains.se.box.BoxImpl;
import ru.geekbrains.se.model.Apple;
import ru.geekbrains.se.model.Orange;
import ru.geekbrains.se.utils.ArrayUtils;
import ru.geekbrains.se.utils.ArrayUtilsImpl;

/**
 * @author Evgeniy Korotchenko
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        // 2. Написать метод, который преобразует массив в ArrayList;
        final String[] strArray = {"1", "2", "3", "4", "5"};
        final ArrayUtils arrayUtils = new ArrayUtilsImpl<>(strArray);
        System.out.println("Entry array:");
        System.out.println(arrayUtils.getArrayList());
        arrayUtils.swapElements(1, 2);
        System.out.println("Swapped 1 and 2 element");
        System.out.println(arrayUtils.getArrayList());
        // 3.
        final Box<Apple> appleBox = new BoxImpl<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        final Box<Apple> smallAppleBox = new BoxImpl<>();
        smallAppleBox.add(new Apple());
        smallAppleBox.add(new Apple());

        final Box<Orange> orangeBox = new BoxImpl<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println();
        System.out.println("Apple box weight - " + appleBox.getWeight());
        System.out.println("Small apple box weight - " + smallAppleBox.getWeight());
        System.out.println("Orange box weight - " + orangeBox.getWeight());

        System.out.println("Comparing second and third box - " + smallAppleBox.compare(orangeBox));
        System.out.println();
        System.out.println("Moving from small apple box to apple box");
        smallAppleBox.move(appleBox);
        System.out.println("Small apple box weight " + smallAppleBox.getWeight());
        System.out.println("Apple box weight - " + appleBox.getWeight());
    }
}
