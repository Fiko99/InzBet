package com.example.inzbet.pojo;

public class HalfTime {
    private String awayTeam;

    private String homeTeam;

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public String toString() {
        return "HalfTime { awayTeam = " + awayTeam + ", homeTeam = " + homeTeam + "}";
    }
}

