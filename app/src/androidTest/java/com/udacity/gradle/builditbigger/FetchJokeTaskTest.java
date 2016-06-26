package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sanju singh on 26-Jun-16.
 */
public class FetchJokeTaskTest extends AndroidTestCase {

    String mJoke = null;
    CountDownLatch signal = null;
    Exception error = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    public void testVerifyReceivedJoke() throws InterruptedException {

        FetchJokeTask fjTask = new FetchJokeTask();
        fjTask.execute(new FetchJokeTask.JokeLoadListener() {
            @Override
            public void onJokeLoad(String joke, Exception e) {
                mJoke = joke;
                error = e;
                signal.countDown();
            }
        });

        signal.await();
        assertNull(error);
        assertNotNull(mJoke);
        assertFalse(mJoke.isEmpty());

    }
}
