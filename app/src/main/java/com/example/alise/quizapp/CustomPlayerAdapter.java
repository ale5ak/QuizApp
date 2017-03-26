package com.example.alise.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alise.quizapp.data.ScoreContract.ScoreEntry;

/**
 * Created by Aleš Pros on 25.03.2017.
 */

public class CustomPlayerAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;

    public CustomPlayerAdapter(Context context, Cursor c) {
        super(context, c);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.player, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView playerName = (TextView) view.findViewById(R.id.player_name);
        TextView playerScore = (TextView) view.findViewById(R.id.player_score);
        TextView playerScoreBarLeft = (TextView) view.findViewById(R.id.player_score_bar_left);
        TextView playerScoreBarRight = (TextView) view.findViewById(R.id.player_score_bar_right);

        String playerNameValue = cursor.getString(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_NAME));
        int playerScoreInt = cursor.getInt(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_SCORE));
        int playerScorePercent = playerScoreInt * 100 / 4;
        String playerScoreValue = "" + playerScorePercent + " %";

        //Set up the left side of percentage bar based on the points achieved
        LinearLayout.LayoutParams paramsScoreBarLeft = (LinearLayout.LayoutParams) playerScoreBarLeft.getLayoutParams();
        paramsScoreBarLeft.weight = playerScorePercent;
        playerScoreBarLeft.setLayoutParams(paramsScoreBarLeft);

        //Set up the right side of percentage bar based on the points achieved
        LinearLayout.LayoutParams paramsScoreBarRight = (LinearLayout.LayoutParams) playerScoreBarRight.getLayoutParams();
        paramsScoreBarRight.weight = 100 - playerScorePercent;
        playerScoreBarRight.setLayoutParams(paramsScoreBarRight);

        playerName.setText(playerNameValue);
        playerScore.setText(playerScoreValue);
    }
}
