package ru.geekbrains.se;

import ru.geekbrains.se.util.ConvertAndSum;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String[][] mass = {{"4", "3", "2", "1"}, {"9", "4", "3", "2"}, {"4", "5", "6", "7"}, {"3", "4", "4", "5"}};
        ConvertAndSum.sumMass(mass);
        String[][] mass2 = {{"4", "3", "2", "4"}, {"4", "3", "2"}, {"4", "5", "6", "7"}, {"3", "4", "4", "5"}};
        ConvertAndSum.sumMass(mass2);
        String[][] mass3 = {{"4", "3", "2", "4"}, {"4", "3", "i2", "4"}, {"4", "5", "6", "7"}, {"3", "4", "4", "5"}};
        ConvertAndSum.sumMass(mass3);
    }
}
