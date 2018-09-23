package ru.geekbrains.se;

import ru.geekbrains.se.base.PhoneBase;
import ru.geekbrains.se.util.WordsArray;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        // 1
        System.out.println("---------------------------------------1---------------------------------------");
        final String[] words = {"Поезд", "Самолет", "Вертолет", "Лодка", "Корабль", "Пароход", "Поезд", "Автомобиль", "Лодка", "Самокат", "Поезд", "Велосипед", "Лодка", "Вертолет"};
        final WordsArray workWithWords = new WordsArray(words);
        System.out.println("Заданный массив:");
        System.out.println(workWithWords.getList());
        System.out.println("Уникальные слова:");
        System.out.println(workWithWords.getUniqueWords());
        System.out.println("Подсчет слов:");
        for (Map.Entry<String, Integer> wordsValues : workWithWords.getWordsCount().entrySet()) {
            System.out.print(wordsValues.getKey() + ": ");
            System.out.print(wordsValues.getValue());
            System.out.println();
        }

        // 2
        System.out.println("---------------------------------------2---------------------------------------");
        PhoneBase phoneBase = new PhoneBase();
        phoneBase.add("Иванов", "+79834234234");
        phoneBase.add("Петров", "+7234234234");
        phoneBase.add("Сидоров", "+234234234234");
        phoneBase.add("Петров", "+3443245");

        System.out.println(phoneBase.findPhonesBySurname("Иванов"));
        System.out.println(phoneBase.findPhonesBySurname("Петров"));
        System.out.println(phoneBase.findPhonesBySurname("Барсик"));
    }
}
