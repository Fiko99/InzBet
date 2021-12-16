package com.example.inzbet;

import java.util.Date;
import java.util.List;

public class Match{

//    public Match(int id, Date utcDate, HomeTeam homeTeam, AwayTeam awayTeam) {
//        this.id = id;
//        this.utcDate = utcDate;
//        this.homeTeam = homeTeam;
//        this.awayTeam = awayTeam;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Date getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(Date utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", season=" + season +
                ", utcDate=" + utcDate +
                ", status='" + status + '\'' +
                ", matchday=" + matchday +
                ", stage='" + stage + '\'' +
                ", group=" + group +
                ", lastUpdated=" + lastUpdated +
                ", score=" + score +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", referees=" + referees +
                ", competition=" + competition +
                ", odds=" + odds +
                '}';
    }

    private int id;
    private Season season;
    private Date utcDate;
    private String status;
    private int matchday;
    private String stage;
    private Object group;
    private Date lastUpdated;
    private Score score;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;
    private List<Referee> referees;
    private Competition competition;
    private Odds odds;
}