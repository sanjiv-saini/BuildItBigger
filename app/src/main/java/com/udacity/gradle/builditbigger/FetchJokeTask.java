package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sanjusingh.builditbigger.jokeapi.myApi.MyApi;

import java.io.IOException;

/**
 * Created by sanju singh on 25-Jun-16.
 */
public class FetchJokeTask extends AsyncTask<FetchJokeTask.JokeLoadListener, Void, String> {
    private static MyApi myApiService = null;
    private JokeLoadListener listener;
    private Exception mError = null;

    public interface JokeLoadListener{
        void onJokeLoad(String joke, Exception e);
    }

    @Override
    protected void onCancelled() {
        if (this.listener != null) {
            mError = new InterruptedException("AsyncTask cancelled");
            this.listener.onJokeLoad(null, mError);
        }
    }

    @Override
    protected String doInBackground(FetchJokeTask.JokeLoadListener... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("http://192.168.42.132:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        listener = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            mError = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
       if(listener != null)
        listener.onJokeLoad(result, mError);
    }
}
