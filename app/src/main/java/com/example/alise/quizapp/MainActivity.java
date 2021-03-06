package com.example.alise.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_NAME = "com.example.alise.quizapp.MESSAGE_NAME";
    public static final String EXTRA_MESSAGE_SCORE = "com.example.alise.quizapp.MESSAGE_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameView = (EditText) findViewById(R.id.name_answer);
        final RadioGroup scrollView = (RadioGroup) findViewById(R.id.scrollview_answer);
        final EditText androidView = (EditText) findViewById(R.id.android_answer);
        final CheckBox primitivesView1 = (CheckBox) findViewById(R.id.primitives_answer1);
        final CheckBox primitivesView2 = (CheckBox) findViewById(R.id.primitives_answer2);
        final CheckBox primitivesView3 = (CheckBox) findViewById(R.id.primitives_answer3);
        final CheckBox primitivesView4 = (CheckBox) findViewById(R.id.primitives_answer4);
        final CheckBox primitivesView5 = (CheckBox) findViewById(R.id.primitives_answer5);
        final RadioGroup colorView = (RadioGroup) findViewById(R.id.color_answer);
        Button submit = (Button) findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameAnswer = nameView.getText().toString();
                int scrollAnswer = scrollView.getCheckedRadioButtonId();
                String androidAnswer = androidView.getText().toString().toLowerCase();
                boolean[] primitivesAnswer = new boolean[5];
                primitivesAnswer[0] = primitivesView1.isChecked();
                primitivesAnswer[1] = !primitivesView2.isChecked();
                primitivesAnswer[2] = primitivesView3.isChecked();
                primitivesAnswer[3] = !primitivesView4.isChecked();
                primitivesAnswer[4] = primitivesView5.isChecked();
                int colorAnswer = colorView.getCheckedRadioButtonId();

                int points = checkAnswers(nameAnswer, scrollAnswer, androidAnswer, primitivesAnswer, colorAnswer);

                if (points != -1) {
                    Intent intent = new Intent(v.getContext(), DisplayScoreActivity.class);
                    intent.putExtra(EXTRA_MESSAGE_NAME, nameAnswer);
                    intent.putExtra(EXTRA_MESSAGE_SCORE, points);
                    startActivity(intent);
                }
            }
        });

    }

    private int checkAnswers(String nameAnswer, int scrollAnswer, String androidAnswer,
                             boolean[] primitivesAnswer, int colorAnswer) {
        int points = 0;

        if (nameAnswer.length() == 0) {
            Toast.makeText(this, "Please fill in your name", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if (scrollAnswer == -1 || androidAnswer.length() == 0 || colorAnswer == -1) {
            Toast.makeText(this, "Please fill in all the answers", Toast.LENGTH_SHORT).show();
            return -1;
        }

        if (scrollAnswer == R.id.scroll_view) {
            points++;
        }

        if (androidAnswer.equals("android")) {
            points++;
        }

        if (areAllTrue(primitivesAnswer)) {
            points++;
        }

        if (colorAnswer == R.id.red) {
            points++;
        }

        return points;
    }

    private boolean areAllTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_score; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, DisplayScoreActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
