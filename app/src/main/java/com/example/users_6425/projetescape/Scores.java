package com.example.users_6425.projetescape;

public class Scores {
    private long id;
    private String name;
    private int score;

    //private String last_tab;
    public Scores(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
        //this.last_tab=last_tab;
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public String getLast_tab() { return last_tab; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //public void setLast_tab(String last_tab) { this.last_tab = last_tab; }
}
