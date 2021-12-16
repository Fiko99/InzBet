package com.example.inzbet;

public class RandomOddsGenerator {

    final float min = 1.01f;
    final float max = 4.0f;
    private float homeOdd, awayOdd, drawOdd;

    public float getHomeOdd() {
        return homeOdd;
    }

    public float getAwayOdd() {
        return awayOdd;
    }

    public float getDrawOdd() {
        return drawOdd;
    }

    private float generateRandomHomeTeamOdd() {
        return (float) Math.random() * (max - min + 1) + min;
    }

    private float generateRandomAwayTeamOdd() {

        if (homeOdd >= 1.01f && homeOdd <= 1.3f) {
            return (homeOdd * 8.5f);
        }

        if (homeOdd > 1.3f && homeOdd < 1.6f) {
            return (homeOdd * 4.5f);
        }

        if (homeOdd >= 1.6f && homeOdd < 2.00f) {
            return (homeOdd * 2.5f);
        }

        if (homeOdd >= 2.0f && homeOdd < 3.0f) {
            return (homeOdd * 1.5f);
        }

        if (homeOdd >= 3.0f && homeOdd < 4.0f) {
            return (homeOdd * 0.7f);
        }

        if (homeOdd >= 4.0f) {
            return (homeOdd * 0.4f);
        }

        return homeOdd;
    }

    private float generateRandomDrawOdd() {
//        float MIN = (awayOdd < homeOdd ) ? awayOdd : homeOdd;
//        float MAX = ( MIN == homeOdd ) ? awayOdd : homeOdd;
//        return (MIN / MAX) + (MAX - MIN);
        float sum = homeOdd + awayOdd;
        if (sum >= 6.0f) {
            return (sum / 1.8f);
        } else {
            return (sum / 1.6f);
        }
    }

    public void generateAllOdds() {
        homeOdd = generateRandomHomeTeamOdd();
        awayOdd = generateRandomAwayTeamOdd();
        drawOdd = generateRandomDrawOdd();
    }
}
