package com.example.inzbet;

public class HomeTeam
{
    private String wins;

    private String draws;

    private String losses;

    public String getWins ()
    {
        return wins;
    }

    public void setWins (String wins)
    {
        this.wins = wins;
    }

    public String getDraws ()
    {
        return draws;
    }

    public void setDraws (String draws)
    {
        this.draws = draws;
    }

    public String getLosses ()
    {
        return losses;
    }

    public void setLosses (String losses)
    {
        this.losses = losses;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [wins = "+wins+", draws = "+draws+", losses = "+losses+"]";
    }
}
