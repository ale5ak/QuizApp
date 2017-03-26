package com.example.alise.quizapp;

import android.app.LoaderManager;
import android.content.ContentValues;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alise.quizapp.data.ScoreDbHelper;
import com.example.alise.quizapp.data.ScoreContract.ScoreEntry;

import java.util.ArrayList;
import java.util.List;

public class DisplayScoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    CustomPlayerAdapter mAdapter;

    /*TODO: put uri into the ScoreContract file*/
    Uri uri =  Uri.parse("content://com.example.alise.quizapp.provider");   //ScoreContract.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int points = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_SCORE, -1);

        mAdapter = new CustomPlayerAdapter(this, null);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one, or start a new one.
        getLoaderManager().initLoader(0, null, this);

        if (points != -1) {
            String nameAnswer = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_NAME);
            String percentPoints = "" + (points * 100 / 4);
            String endOfTheSentence = points == 0 ? "." : "!";
            Toast.makeText(this, "You achieved " + percentPoints + " % of the correct answers" + endOfTheSentence, Toast.LENGTH_SHORT).show();
            writeToDatabase(nameAnswer, points);
        }
    }

    private void writeToDatabase(String nameAnswer, int points) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ScoreEntry.COLUMN_NAME, nameAnswer);
        values.put(ScoreEntry.COLUMN_SCORE, points);

        // Insert the new row, returning the primary key value of the new row
        getContentResolver().insert(uri, values);
    }


    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.  This
        // sample only has one Loader, so we don't care about the ID.
        // First, pick the base URI to use depending on whether we are
        // currently filtering.

        String[] projection = {
                ScoreEntry._ID,
                ScoreEntry.COLUMN_NAME,
                ScoreEntry.COLUMN_SCORE
        };

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, uri, projection, null, null, ScoreEntry.COLUMN_SCORE + " DESC");
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }
}
