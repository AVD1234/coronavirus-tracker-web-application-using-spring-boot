package com.example.CoronaVirusTracker.models;

public class Locationstats {

    private String state;
    private String country;
    private Integer latestTotalcases;
    private Integer setDiffdiffFromPrevDay;

    public Integer getSetDiffdiffFromPrevDay() {
        return setDiffdiffFromPrevDay;
    }

    public void setSetDiffdiffFromPrevDay(Integer setDiffdiffFromPrevDay) {
        this.setDiffdiffFromPrevDay = setDiffdiffFromPrevDay;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLatestTotalcases() {
        return latestTotalcases;
    }

    public void setLatestTotalcases(Integer latestTotalcases) {
        this.latestTotalcases = latestTotalcases;
    }





    @Override
    public String toString() {
        return "Locationstats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalcases='" + latestTotalcases + '\'' +
                '}';
    }
}
