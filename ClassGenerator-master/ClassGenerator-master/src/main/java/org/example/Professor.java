package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Professor {

    private int professorId;
    @JsonProperty("Professor")
    private String name;
    @JsonProperty("Subject")
    private String subject;
    @JsonProperty("Rating")
    private double rating;
    @JsonProperty("NumOfRating")
    private int numOfRatings;
    @JsonProperty("Difficulty")
    private double difficulty;
    private double bayesianProbability;

    public Professor(int professorId, String name, String subject, float rating, int numOfRatings, float difficulty) {
        this.professorId = professorId;
        this.name = name;
        this.subject = subject;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.difficulty = difficulty;
    }

    public Professor(String name, float rating, int numOfRatings, float difficulty) {
        this.name = name;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public double getBayesianProbability() {
        return bayesianProbability;
    }

    public void setBayesianProbability(double bayesianProbability) {
        this.bayesianProbability = bayesianProbability;
    }

    @Override
    public String toString() {
        return "'" + name + '\'' +
                ", rate:" + rating +
                ", NumRate:" + numOfRatings +
                ", Hardness:" + difficulty;
    }
}