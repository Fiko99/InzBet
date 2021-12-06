package com.example.inzbet;

public class Score
{
    private String duration;

    private String winner;

    private HalfTime halfTime;

    private FullTime fullTime;

    private ExtraTime extraTime;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getWinner ()
    {
        return winner;
    }

    public void setWinner (String winner)
    {
        this.winner = winner;
    }

    public HalfTime getHalfTime ()
    {
        return halfTime;
    }

    public void setHalfTime (HalfTime halfTime)
    {
        this.halfTime = halfTime;
    }

    public FullTime getFullTime ()
    {
        return fullTime;
    }

    public void setFullTime (FullTime fullTime)
    {
        this.fullTime = fullTime;
    }

    public ExtraTime getExtraTime ()
    {
        return extraTime;
    }

    public void setExtraTime (ExtraTime extraTime)
    {
        this.extraTime = extraTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", winner = "+winner+", halfTime = "+halfTime+", fullTime = "+fullTime+", extraTime = "+extraTime+"]";
    }
}
