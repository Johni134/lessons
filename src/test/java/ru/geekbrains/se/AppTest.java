package ru.geekbrains.se;

import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.se.util.ArrayFillTest;

import static org.junit.Assert.assertTrue;

public class AppTest {
    private ArrayFillTest arrayFillTest;

    @Before
    public void setUp() {
        arrayFillTest = new ArrayFillTest();
    }

    @Test
    public void testSimpleMethod() {
        System.out.println(arrayFillTest.testSimpleMethod());
    }

    @Test
    public void testMethodWithOneThread() {
        System.out.println(arrayFillTest.testMethodWithDividingArrayByThreads(1));
    }

    @Test
    public void testMethodWithTwoThreads() {
        System.out.println(arrayFillTest.testMethodWithDividingArrayByThreads(2));
    }

    @Test
    public void testMethodWithThreeThreads() {
        System.out.println(arrayFillTest.testMethodWithDividingArrayByThreads(3));
    }

    @Test
    public void compareMethodsWithDifThreads() {
        assertTrue(arrayFillTest.testMethodWithDividingArrayByThreads(1) > arrayFillTest.testMethodWithDividingArrayByThreads(2));
    }

    @Test
    public void compareDifMethods() {
        assertTrue(arrayFillTest.testSimpleMethod() > arrayFillTest.testMethodWithDividingArrayByThreads(2));
    }
}
