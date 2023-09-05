package io.github.a_c_rodriguez.TheOne.Api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDTO {
    private String movieId;
    private String name;
    private int runtimeInMinutes;
    private double budgetInMillions;
    private double boxOfficeRevenueInMillions;
    private int academyAwardNominations;
    private int academyAwardWins;
    private double rottenTomatoesScore;

    @JsonProperty("_id")
    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String _id) {
        this.movieId = _id;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("runtimeInMinutes")
    public int getRuntimeInMinutes() {
        return this.runtimeInMinutes;
    }

    public void setRuntimeInMinutes(int runtimeInMinutes) {
        this.runtimeInMinutes = runtimeInMinutes;
    }

    @JsonProperty("budgetInMillions")
    public double getBudgetInMillions() {
        return this.budgetInMillions;
    }

    public void setBudgetInMillions(double budgetInMillions) {
        this.budgetInMillions = budgetInMillions;
    }

    @JsonProperty("boxOfficeRevenueInMillions")
    public double getBoxOfficeRevenueInMillions() {
        return this.boxOfficeRevenueInMillions;
    }

    public void setBoxOfficeRevenueInMillions(double boxOfficeRevenueInMillions) {
        this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
    }

    @JsonProperty("academyAwardNominations")
    public int getAcademyAwardNominations() {
        return this.academyAwardNominations;
    }

    public void setAcademyAwardNominations(int academyAwardNominations) {
        this.academyAwardNominations = academyAwardNominations;
    }

    @JsonProperty("academyAwardWins")
    public int getAcademyAwardWins() {
        return this.academyAwardWins;
    }

    public void setAcademyAwardWins(int academyAwardWins) {
        this.academyAwardWins = academyAwardWins;
    }

    @JsonProperty("rottenTomatoesScore")
    public double getRottenTomatoesScore() {
        return this.rottenTomatoesScore;
    }

    public void setRottenTomatoesScore(double rottenTomatoesScore) {
        this.rottenTomatoesScore = rottenTomatoesScore;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id='" + movieId + '\'' +
                ", name='" + name + '\'' +
                ", runtimeInMinutes=" + runtimeInMinutes +
                ", budgetInMillions=" + budgetInMillions +
                ", boxOfficeRevenueInMillions=" + boxOfficeRevenueInMillions +
                ", academyAwardNominations=" + academyAwardNominations +
                ", academyAwardWins=" + academyAwardWins +
                ", rottenTomatoesScore=" + rottenTomatoesScore +
                '}';
    }
}
