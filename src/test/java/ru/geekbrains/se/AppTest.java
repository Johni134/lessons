package ru.geekbrains.se;

import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.se.box.Box;
import ru.geekbrains.se.box.BoxImpl;
import ru.geekbrains.se.model.Apple;
import ru.geekbrains.se.model.Orange;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Box<Apple> appleBox;
    private Box<Apple> secondAppleBox;
    private Box<Orange> orangeBox;

    @Before
    public void initialize() {
        appleBox = new BoxImpl<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        orangeBox = new BoxImpl<>();
        orangeBox.add(new Orange());
        secondAppleBox = new BoxImpl<>();
    }

    @Test
    public void compareBoxes() {
        assertFalse(appleBox.compare(orangeBox));
    }

    @Test
    public void boxIsEmptyAfterMove() {
        appleBox.move(secondAppleBox);
        assertTrue(appleBox.getFruits().isEmpty());
    }
}
