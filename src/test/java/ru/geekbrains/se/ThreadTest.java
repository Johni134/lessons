package ru.geekbrains.se;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ThreadTest {

    private static final int COUNT_ORDER_NUM = 3;

    @Test
    public void testLetters() {
        final ExecutorService executorService = Executors.newFixedThreadPool(COUNT_ORDER_NUM);
        final OrderControl orderControl = new OrderControl(COUNT_ORDER_NUM);
        final String letters[] = {"A", "B", "C", "D", "E"};

        assertTrue(COUNT_ORDER_NUM <= letters.length);

        for (int i = 0; i < COUNT_ORDER_NUM; i++) {
            executorService.submit(new PrintLetterThread(letters[i], orderControl, i + 1));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
    }

    // class for order control
    class OrderControl {
        final private int countOrderNum;
        private int currentOrderNum;

        OrderControl(final int countOrderNum) {
            this.countOrderNum = countOrderNum;
            this.currentOrderNum = 1;
        }

        synchronized void setNextCurrentOrderNum() {
            currentOrderNum = (countOrderNum == currentOrderNum ? 1 : currentOrderNum + 1);
            notifyAll();
        }

        @SneakyThrows
        synchronized void waitForCurrentOrder(final int orderNum) {
            while (currentOrderNum != orderNum) {
                wait();
            }
        }
    }

    class PrintLetterThread implements Runnable {
        final private String letter;
        final private int orderNum;
        final private OrderControl orderControl;

        PrintLetterThread(final String letter, final OrderControl orderControl, final int orderNum) {
            this.letter = letter;
            this.orderNum = orderNum;
            this.orderControl = orderControl;
        }

        @Override
        @SneakyThrows
        public void run() {
            for (int i = 5; i > 0; i--) {
                orderControl.waitForCurrentOrder(orderNum);
                System.out.println(letter);
                Thread.sleep(100);
                orderControl.setNextCurrentOrderNum();
            }
        }
    }
}
