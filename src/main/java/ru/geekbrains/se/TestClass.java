package ru.geekbrains.se;

import com.sun.istack.internal.NotNull;
import ru.geekbrains.se.annotations.AfterSuite;
import ru.geekbrains.se.annotations.BeforeSuite;
import ru.geekbrains.se.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestClass {

    private int testIterator = 0;

    public static void start(@NotNull final String curClassStr) {
        try {
            start(Class.forName(curClassStr));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(@NotNull final Class curClass) {

        // init variables
        final List<Method> afterSuiteList = new ArrayList<>();
        final List<Method> beforeSuiteList = new ArrayList<>();
        final Map<Integer, Method> treeMapTests = new TreeMap<>();
        final Method[] methods = curClass.getDeclaredMethods();

        // separate methods
        for (Method method : methods) {

            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteList.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteList.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                treeMapTests.put(method.getAnnotation(Test.class).priority(), method);
            }
        }

        // raising error
        if (afterSuiteList.size() > 1) {
            throw new RuntimeException("More then 2 AfterSuite annotations");
        }

        if (beforeSuiteList.size() > 1) {
            throw new RuntimeException("More than 2 BeforeSuite annotations");
        }

        // invoking methods
        try {
            Object classObject = curClass.getConstructor().newInstance();
            if (beforeSuiteList.size() == 1) {
                beforeSuiteList.get(0).invoke(classObject);
            }
            for (Map.Entry treeMapEntry : treeMapTests.entrySet()) {
                ((Method) treeMapEntry.getValue()).invoke(classObject);
            }
            if (afterSuiteList.size() == 1) {
                afterSuiteList.get(0).invoke(classObject);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("testAfterSuite: testIterator = " + testIterator);
    }

    @Test(priority = 4)
    public void testAddI() {
        testIterator++;
        System.out.println("testAddI: testIterator = " + testIterator);
    }

    @Test(priority = 3)
    public void testAddDoubleI() {
        testIterator++;
        testIterator++;
        System.out.println("testAddDoubleI: testIterator = " + testIterator);
    }

    @Test
    public void justTest() {
        System.out.println("justTest: testIterator = " + testIterator);
    }

    @BeforeSuite
    public void testBeforeSuite() {
        testIterator = 1;
        System.out.println("testBeforeSuite: testIterator = " + testIterator);
    }
}
