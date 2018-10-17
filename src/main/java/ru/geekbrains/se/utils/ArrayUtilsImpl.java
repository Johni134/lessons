package ru.geekbrains.se.utils;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class ArrayUtilsImpl<T> implements ArrayUtils {

    @NotNull
    private T[] array;

    public ArrayUtilsImpl(@NotNull final T[] array) {
        this.array = array;
    }

    /**
     * Swapping elements in array
     *
     * @param firstPos  first position to swap
     * @param secondPos second position to swap
     */
    @Override
    public void swapElements(final int firstPos, final int secondPos) {
        T tmpValue;

        if (firstPos == secondPos) {
            System.out.println("First position = second position!");
            return;
        }
        if (array.length <= firstPos || array.length <= secondPos) {
            System.out.println("First or second position is outside the array!");
            return;
        }

        tmpValue = array[secondPos];
        array[secondPos] = array[firstPos];
        array[firstPos] = tmpValue;
    }

    @Override
    public ArrayList<T> getArrayList() {
        return new ArrayList<>(Arrays.asList(array));
    }
}
