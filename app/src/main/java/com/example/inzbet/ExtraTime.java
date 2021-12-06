package com.example.inzbet;

public class ExtraTime
{
    private String awayTeam;

    private String homeTeam;

    public String getAwayTeam ()
{
    return awayTeam;
}

    public void setAwayTeam (String awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam ()
{
    return homeTeam;
}

    public void setHomeTeam (String homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [awayTeam = "+awayTeam+", homeTeam = "+homeTeam+"]";
    }
}
