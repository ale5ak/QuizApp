package com.example.alise.quizapp;

/**
 * Created by Aleš Pros on 25.03.2017.
 */

public class Player {
    private String mName;
    private int mScore;

    Player(String name, int score) {
        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public int getScore() {
        return mScore;
    }
}
