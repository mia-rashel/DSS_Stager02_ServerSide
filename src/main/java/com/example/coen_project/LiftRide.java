package com.example.coen_project;

public class LiftRide {
    private int resortId;
    private int seasonId;
    private int dayId;
    private int skierId;
    private int liftId;
    private int time;

    // Default constructor
    public LiftRide() {
        // Default constructor is required for Jackson deserialization
    }

    // Parameterized constructor
    public LiftRide(int resortId, int seasonId, int dayId, int skierId, int liftId, int time) {
        this.resortId = resortId;
        this.seasonId = seasonId;
        this.dayId = dayId;
        this.skierId = skierId;
        this.liftId = liftId;
        this.time = time;
    }

    // Getters and setters
    public int getResortId() {
        return resortId;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getSkierId() {
        return skierId;
    }

    public void setSkierId(int skierId) {
        this.skierId = skierId;
    }

    public int getLiftId() {
        return liftId;
    }

    public void setLiftId(int liftId) {
        this.liftId = liftId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
