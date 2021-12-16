package com.example.inzbet.pojo;

public class Score {
    @Override
    public String toString() {
        return "Score{" +
                "winner='" + winner + '\'' +
                ", duration='" + duration + '\'' +
                ", fullTime=" + fullTime +
                ", halfTime=" + halfTime +
                ", extraTime=" + extraTime +
                ", penalties=" + penalties +
                '}';
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public FullTime getFullTime() {
        return fullTime;
    }

    public void setFullTime(FullTime fullTime) {
        this.fullTime = fullTime;
    }

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    public ExtraTime getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(ExtraTime extraTime) {
        this.extraTime = extraTime;
    }

    public Penalties getPenalties() {
        return penalties;
    }

    public void setPenalties(Penalties penalties) {
        this.penalties = penalties;
    }

    private String winner;
    private String duration;
    private FullTime fullTime;
    private HalfTime halfTime;
    private ExtraTime extraTime;
    private Penalties penalties;
}
