package com.mygdx.game;

public class Player {
    private String name;
    private int score;
    private boolean isPlaying;

    // Default constructor
    public Player() {
        // do nothing
        this.name = "Player";
        this.score = 0;
        this.isPlaying = true;
    }

    // Constructor without isPlaying set
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.isPlaying = true;
    }

    // Constructor with all methods
    public Player(String name, int score, boolean isPlaying) {
        this.name = name;
        this.score = score;
        this.isPlaying = isPlaying;
    }

    // Getter methods
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public boolean isPlaying() {
        return isPlaying;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
