package com.example.inzbet.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Odds {
    private float homeTeamOdd;
    private float awayTeamOdd;
    private float drawOdd;

    public Odds() {
    }

    public Odds(float homeTeamOdd, float awayTeamOdd, float drawOdd) {
        this.homeTeamOdd = homeTeamOdd;
        this.awayTeamOdd = awayTeamOdd;
        this.drawOdd = drawOdd;
    }

    public float getHomeTeamOdd() {
        return homeTeamOdd;
    }

    public void setHomeTeamOdd(float homeTeamOdd) {
        this.homeTeamOdd = homeTeamOdd;
    }

    public float getAwayTeamOdd() {
        return awayTeamOdd;
    }

    public void setAwayTeamOdd(float awayTeamOdd) {
        this.awayTeamOdd = awayTeamOdd;
    }

    public float getDrawOdd() {
        return drawOdd;
    }

    public void setDrawOdd(float drawOdd) {
        this.drawOdd = drawOdd;
    }

    @Override
    public String toString() {
        return "Odds{" +
                "homeTeamOdd=" + homeTeamOdd +
                ", awayTeamOdd=" + awayTeamOdd +
                ", drawOdd=" + drawOdd +
                '}';
    }
}
