package com.example.inzbet;

public class Penalties{
    @Override
    public String toString() {
        return "Penalties{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }

    public Object getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Object homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Object getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Object awayTeam) {
        this.awayTeam = awayTeam;
    }

    private Object homeTeam;
    private Object awayTeam;
}
