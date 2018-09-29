package ru.geekbrains.se.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * test array filling
 */
public class ArrayFillTest {

    // fixed array capacity
    private final int size = 10000000;
    private final int h = size / 2;
    private final float[] arr = new float[size];

    /**
     * fill arr by 1F values
     */
    private void initArray() {
        Arrays.fill(arr, 1f);
    }

    /**
     * test simple method without threads
     */
    public void testSimpleMethod() {
        initArray();
        final long a = System.currentTimeMillis();
        fillArrayByFunction(arr, size, 0);
        System.out.println(System.currentTimeMillis() - a);
    }

    /**
     * fill array by function using size of array and begin value to support correct values
     *
     * @param array       array, that we need to fill
     * @param currentSize size of array
     * @param beginValue  value for supporting correct values
     */
    private void fillArrayByFunction(float[] array, int currentSize, int beginValue) {
        for (int i = 0; i < currentSize; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i + beginValue) / 5.0f)
                    * Math.cos(0.2f + (i + beginValue) / 5.0f)
                    * Math.cos(0.4f + (i + beginValue) / 2.0f));
        }
    }

    /**
     * test methods with different arrays count
     *
     * @param arrayCount we can use different arrays count
     */
    public void testMethodWithDividingArrayByThreads(int arrayCount) {
        initArray();
        final long a = System.currentTimeMillis();
        divideArrayAndFillByFunctionInThreads(arrayCount);
        System.out.println(System.currentTimeMillis() - a);
    }

    /**
     * divide array and fill arrays in threads
     *
     * @param arrayCount count of arrays
     */
    private void divideArrayAndFillByFunctionInThreads(int arrayCount) {
        // size for each array
        int eachSize = size / arrayCount;
        // size for copy (this maked for the last array)
        int sizeForCopy = size / arrayCount;
        // array list for threads
        ArrayList<Thread> threadsList = new ArrayList<>();

        // if array count is too big
        if (eachSize <= 1) eachSize = 1;

        for (int i = 0; i < arrayCount; i++) {
            if (i == arrayCount - 1) {
                // last array can be other size
                sizeForCopy = size - eachSize * i;
            }
            // finalize variables for adding in the thread
            final int finalSizeForCopy = sizeForCopy;
            final int finalEachSize = eachSize;
            final int finalI = i;
            // adding new thread
            threadsList.add(new Thread(() -> {
                float[] tempArray = new float[finalSizeForCopy];
                // finalI * finalEachSize - this is shift in the main array to divide
                System.arraycopy(arr, finalI * finalEachSize, tempArray, 0, finalSizeForCopy);
                fillArrayByFunction(tempArray, finalSizeForCopy, finalI * finalEachSize);
                System.arraycopy(tempArray, 0, arr, finalI * finalEachSize, finalSizeForCopy);
            }));
        }

        // starting threads
        for (Thread curThread :
                threadsList) {
            curThread.start();
        }

        // waiting all threads
        for (Thread curThread :
                threadsList) {
            try {
                curThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // clearing list of threads
        threadsList.clear();
    }
}
