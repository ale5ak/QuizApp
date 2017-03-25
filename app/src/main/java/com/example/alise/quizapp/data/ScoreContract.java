package com.example.alise.quizapp.data;

import android.provider.BaseColumns;

/**
 * Created by Aleš Pros on 25.03.2017.
 */

public final class ScoreContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ScoreContract() {}

    /* Inner class that defines the table contents */
    public static class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "score";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCORE = "score";
    }
}
