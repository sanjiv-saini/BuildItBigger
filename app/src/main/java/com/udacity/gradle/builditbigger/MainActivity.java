package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokedisplayer.JokeActivity;


public class MainActivity extends ActionBarActivity implements FetchJokeTask.JokeLoadListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        new FetchJokeTask().execute(this);
    }

    @Override
    public void onJokeLoad(String joke, Exception e){
        if(e != null){
            joke = e.getMessage();
        }
        showJoke(joke);
    }

    void showJoke(String joke){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(intent.EXTRA_TEXT, joke);
        //Toast.makeText(this, jt.getJoke(), Toast.LENGTH_SHORT).show();

        this.startActivity(intent);
    }

}
