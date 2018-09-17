package ru.geekbrains.se;

import ru.geekbrains.se.model.*;

public class App {
    public static void main(String[] args) {
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"), new Human("Иван")};
        Obstacle[] course = {new Cross(80), new Wall(2), new Wall(10), new Cross(120)};
        Course c = new Course(course);
        Team team = new Team("Команда \"Бублик\"", competitors);
        c.doIt(team);
        team.showResults();

    }
}



