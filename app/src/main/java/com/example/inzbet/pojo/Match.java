package com.example.inzbet.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Match implements Parcelable {

    protected Match(Parcel in) {
        id = in.readInt();
        homeTeam = new HomeTeam();
        awayTeam = new AwayTeam();
        odds = new Odds();
        homeTeam.setName(in.readString());
        awayTeam.setName(in.readString());
        type = in.readString();
        odds.setHomeTeamOdd(in.readFloat());
        odds.setAwayTeamOdd(in.readFloat());
        odds.setDrawOdd(in.readFloat());
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    private String type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(homeTeam.getName());
        dest.writeString(awayTeam.getName());
        dest.writeString(type);
        dest.writeFloat(odds.getHomeTeamOdd());
        dest.writeFloat(odds.getAwayTeamOdd());
        dest.writeFloat(odds.getDrawOdd());
    }
}