package com.example.alise.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.alise.quizapp.data.ScoreContract.ScoreEntry;

import java.util.ArrayList;

/**
 * Created by Aleš Pros on 25.03.2017.
 */

public class CustomPlayerAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;

    public CustomPlayerAdapter(Context context, Cursor c) {
        super (context, c);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.player, null); //maybe there should be condition if(view==null)
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView playerName = (TextView) view.findViewById(R.id.player_name);
        TextView playerScore = (TextView) view.findViewById(R.id.player_score);

        String playerNameValue = cursor.getString(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_NAME));
        int playerScoreInt = cursor.getInt(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_SCORE));
        String playerScoreValue = "" + (playerScoreInt * 100 / 4) + " %";

        playerName.setText(playerNameValue);
        playerScore.setText(playerScoreValue);
    }
}
