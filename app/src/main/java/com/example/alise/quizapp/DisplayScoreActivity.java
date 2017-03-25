package com.example.alise.quizapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alise.quizapp.data.ScoreDbHelper;
import com.example.alise.quizapp.data.ScoreContract.ScoreEntry;

import java.util.ArrayList;
import java.util.List;

public class DisplayScoreActivity extends AppCompatActivity {
    ScoreDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mDbHelper = new ScoreDbHelper(this);
        readFromDatabase();
    }

    private void writeToDatabase() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ScoreEntry.COLUMN_NAME, "Aleš");
        values.put(ScoreEntry.COLUMN_SCORE, 12);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ScoreEntry.TABLE_NAME, null, values);
        Toast.makeText(this, "" + newRowId, Toast.LENGTH_SHORT).show();
    }

    private void readFromDatabase() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ScoreEntry._ID,
                ScoreEntry.COLUMN_NAME,
                ScoreEntry.COLUMN_SCORE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ScoreEntry.COLUMN_SCORE + " DESC";

        Cursor cursor = db.query(
                ScoreEntry.TABLE_NAME,                    // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Player> players = new ArrayList<Player>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(ScoreEntry.COLUMN_SCORE));
            players.add(new Player(name, score));
        }
        cursor.close();

        CustomPlayerAdapter adapter = new CustomPlayerAdapter(this, android.R.layout.simple_list_item_1, players);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


    }
}
