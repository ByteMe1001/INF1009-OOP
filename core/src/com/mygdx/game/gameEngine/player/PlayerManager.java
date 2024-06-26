package com.mygdx.game.gameEngine.player;

import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<Player> playerList;

    public PlayerManager() {
        this.playerList = new ArrayList<>();
    }

    public void createPlayer(String name) {
        Player player = new Player(name);
        playerList.add(player);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public String getName(Player p) {
        return findPlayer(p).getName();
    }
    public int getScore(Player p) {
        return findPlayer(p).getScore();
    }
    public int getScore() {
        return playerList.get(0).getScore();
    }

    public boolean isPlaying(Player p) {
        return findPlayer(p).isPlaying();
    }

    // Setter methods
    public void setName(Player p, String name) {
       findPlayer(p).setName(name);
    }

    public void setScore(Player p, int score) {
        findPlayer(p).setScore(score);
    }
    public void setScore(int score) {
        playerList.get(0).setScore(score);
    }

    public void setPlaying(Player p, boolean playing) {
        findPlayer(p).setPlaying(playing);
    }


    private Player findPlayer(Player p) {
        for (Player player : playerList) {
            if (player == p) {
                return player;
            }
        }
        throw new IllegalArgumentException("Player not found in the player list");
    }
}

