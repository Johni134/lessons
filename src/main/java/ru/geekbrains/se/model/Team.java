package ru.geekbrains.se.model;

public class Team {
    private String title;
    private Competitor[] competitors;

    public Team(String title, Competitor[] competitors) {
        this.title = title;
        this.competitors = competitors;
    }

    public void showCompetitorsOnDistance() {
        System.out.println("Участники команды \"" + title + "\", прошедшие препятствия:");
        for (Competitor c : competitors) {
            if (c.isOnDistance())
                c.getName();
        }
    }

    public void showResults() {
        System.out.println("Результаты команды \"" + title + "\":");
        for (Competitor c : competitors) {
            c.info();
        }
    }

    Competitor[] getCompetitors() {
        return competitors;
    }
}
