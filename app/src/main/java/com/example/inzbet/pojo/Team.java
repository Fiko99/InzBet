package com.example.inzbet.pojo;

public class Team {
    @Override
    public String toString() {
        return "Team{" +
                "wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int wins;
    private int draws;
    private int losses;
    private int id;
    private String name;
}

