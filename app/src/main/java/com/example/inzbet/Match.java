package com.example.inzbet;

public class Match
{
    private String venue;

    private Integer matchday;

    private AwayTeam awayTeam;

    private Competition competition;

    private String utcDate;

    private Integer minute;

    private String lastUpdated;

    private Score score;

    private String stage;

    private HomeTeam homeTeam;

    private String id;

    private String attendance;

    private String status;

    private String group;

    public String getVenue ()
    {
        return venue;
    }

    public void setVenue (String venue)
    {
        this.venue = venue;
    }

    public Integer getMatchday ()
{
    return matchday;
}

    public void setMatchday (Integer matchday)
    {
        this.matchday = matchday;
    }

    public AwayTeam getAwayTeam ()
    {
        return awayTeam;
    }

    public void setAwayTeam (AwayTeam awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public Competition getCompetition ()
    {
        return competition;
    }

    public void setCompetition (Competition competition)
    {
        this.competition = competition;
    }

    public String getUtcDate ()
    {
        return utcDate;
    }

    public void setUtcDate (String utcDate)
    {
        this.utcDate = utcDate;
    }

    public Integer getMinute ()
{
    return minute;
}

    public void setMinute (Integer minute)
    {
        this.minute = minute;
    }

    public String getLastUpdated ()
    {
        return lastUpdated;
    }

    public void setLastUpdated (String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public Score getScore ()
    {
        return score;
    }

    public void setScore (Score score)
    {
        this.score = score;
    }

    public String getStage ()
    {
        return stage;
    }

    public void setStage (String stage)
    {
        this.stage = stage;
    }

    public HomeTeam getHomeTeam ()
    {
        return homeTeam;
    }

    public void setHomeTeam (HomeTeam homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAttendance ()
    {
        return attendance;
    }

    public void setAttendance (String attendance)
    {
        this.attendance = attendance;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getGroup ()
    {
        return group;
    }

    public void setGroup (String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [venue = "+venue+", matchday = "+matchday+", awayTeam = "+awayTeam+", competition = "+competition+", utcDate = "+utcDate+", minute = "+minute+", lastUpdated = "+lastUpdated+", score = "+score+", stage = "+stage+", homeTeam = "+homeTeam+", id = "+id+", attendance = "+attendance+", status = "+status+", group = "+group+"]";
    }
}
