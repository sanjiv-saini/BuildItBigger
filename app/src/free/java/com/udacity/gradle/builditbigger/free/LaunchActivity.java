package com.udacity.gradle.builditbigger.free;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.MainActivity;

/**
 * Created by sanju singh on 27-Jun-16.
 */
public class LaunchActivity extends MainActivity{

    private InterstitialAd mInterstitialAd;
    private View mClickedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginTellingJoke(mClickedView);
            }
        });

        requestNewInterstitial();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tellJoke(View view) {
        mClickedView = view;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            beginTellingJoke(view);
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("4d00aed8b4ec60db")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void beginTellingJoke(View view){
        super.tellJoke(view);
    }
}
