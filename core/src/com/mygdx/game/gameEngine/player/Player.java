package com.mygdx.game.gameEngine.player;


// To store player state and variables throughout the whole application, even through multiple gamecycle rounds
public class Player {
    private String name;
    private int score;
    private boolean isPlaying;

    // Default constructor
    public Player() {
        // do nothing
        this.name = "Player 1";
        this.score = 0;
        this.isPlaying = true;
    }

    public Player(String name) {
        // do nothing
        this.name = name;
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


