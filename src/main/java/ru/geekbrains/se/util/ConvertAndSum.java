package ru.geekbrains.se.util;

import ru.geekbrains.se.exception.MyArrayDataException;
import ru.geekbrains.se.exception.MyArraySizeException;
import ru.geekbrains.se.exception.MyBaseException;

public class ConvertAndSum {

    public static void sumMass(String[][] mass) {
        System.out.println("Пытаемся суммировать массив:");
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++) {
                System.out.print("{" + mass[i][j] + "} ");
            }
            System.out.println();
        }
        try {
            System.out.println("Сумма: " + ConvertAndSum.convertToIntAndSum(mass));
        } catch (MyBaseException e) {
            System.out.println(e.getCustomErrorInfo());
            e.printStackTrace();
        }
    }

    private static int convertToIntAndSum(String[][] mass) throws MyArraySizeException, MyArrayDataException {

        int sum = 0;
        boolean isCorrectSize4x4 = true;
        if (mass.length != 4) {
            isCorrectSize4x4 = false;
        } else {
            for (String[] partMass : mass) {
                if (partMass.length != 4) {
                    isCorrectSize4x4 = false;
                    break;
                }
            }
        }
        if (!isCorrectSize4x4) {
            throw new MyArraySizeException("Неверный размер массива! Должен быть 4х4!");
        }
        for (int i = 0; i < mass.length; i++)
            for (int j = 0; j < mass[i].length; j++) {
                try {
                    sum += Integer.parseInt(mass[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Ошибка преобразования к int!", i, j);
                }
            }

        return sum;
    }

}
