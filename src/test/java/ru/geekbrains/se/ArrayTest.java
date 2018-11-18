package ru.geekbrains.se;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class ArrayTest {

    private Integer[] testValues;
    private CheckArray checkArray;

    public ArrayTest(Integer[] testValues) {
        this.testValues = testValues;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Integer[][][]{
                {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
                {{3, 3, 4, 9, 1, 3, 2}},
                {{2, 2, 2, 2, 3}},
                {{5, 5, 10, 11, 12, 4}}
        });
    }

    @Before
    public void init() {
        checkArray = new CheckArray();
    }

    @Test
    public void checkArrayLastFourNum() {
        System.out.println(Arrays.toString(checkArray.getArrayAfterLastFourNum(testValues)));
    }

    @Test(expected = RuntimeException.class)
    public void checkArrayExpectedRuntime() {
        System.out.println(Arrays.toString(checkArray.getArrayAfterLastFourNum(testValues)));
    }

    @Test
    public void checkCounts() {
        Assert.assertNotEquals(testValues.length, checkArray.getArrayAfterLastFourNum(testValues));
    }

    @Test
    public void checkContainsOneOrFour() {
        Assert.assertTrue(checkArray.arrayContainsOneOrFour(testValues));
    }

    @Test
    public void checkNotContainsOneOrFour() {
        Assert.assertFalse(checkArray.arrayContainsOneOrFour(testValues));
    }

    class CheckArray {

        Integer[] getArrayAfterLastFourNum(final Integer[] arrayWithFour) throws RuntimeException {

            final List<Integer> listWithFour = Arrays.asList(arrayWithFour);
            final LinkedList<Integer> newLinkedList = new LinkedList<>();

            if (!listWithFour.contains(4))
                throw new RuntimeException("There are no 4 num in the array!");

            for (int i = listWithFour.size() - 1; i >= 0; i--) {
                Integer currentValue = listWithFour.get(i);
                if (currentValue == 4)
                    break;
                newLinkedList.addFirst(currentValue);
            }

            return newLinkedList.toArray(new Integer[0]);

        }

        boolean arrayContainsOneOrFour(Integer[] arrayWithOneOrFour) {
            List<Integer> listWithFour = Arrays.asList(arrayWithOneOrFour);
            return listWithFour.stream().anyMatch(integer -> (integer == 4 || integer == 1));
        }
    }
}
