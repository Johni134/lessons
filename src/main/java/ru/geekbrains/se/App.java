package ru.geekbrains.se;

import ru.geekbrains.se.util.ArrayFillTest;

/**
 * App class
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayFillTest arrayFillTest = new ArrayFillTest();
        // testing simple method without threads
        System.out.println(arrayFillTest.testSimpleMethod());
        // testing method, which divide array for 2 parts and fill it in threads
        System.out.println(arrayFillTest.testMethodWithDividingArrayByThreads(2));
    }
}
