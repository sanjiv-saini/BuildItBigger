package com.example.jokedisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeView = (TextView) findViewById(R.id.jokeView);

        Intent intent = this.getIntent();
        String joke = intent.getStringExtra(intent.EXTRA_TEXT);

        jokeView.setText(joke);

    }
}
