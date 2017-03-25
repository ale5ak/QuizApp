package com.example.alise.quizapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aleš Pros on 25.03.2017.
 */

public class CustomPlayerAdapter extends ArrayAdapter<Player> {

    public CustomPlayerAdapter(Context context, int resource, ArrayList<Player> objects) {
        super (context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.player, null);
        }

        Player player = getItem(position);

        TextView playerName = (TextView) view.findViewById(R.id.player_name);
        TextView playerScore = (TextView) view.findViewById(R.id.player_score);

        playerName.setText(player.getName());
        playerScore.setText("" + player.getScore());

        return view;

    }

}
