 package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

 public class MainActivity extends AppCompatActivity {

     public class DownloadTask extends AsyncTask<String, Void, String> { // class which will do the download work

        @Override
        protected String doInBackground(String... urls) {
            // Log.i("URL", strings[0]); // printing the passed string with log
            // extracting html from URL
            String result = null;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]); // converting string into url
                urlConnection = (HttpURLConnection) url.openConnection(); // connecting with the url
                InputStream in = urlConnection.getInputStream(); //  creating a data stream with url
                InputStreamReader reader = new InputStreamReader(in); // reading object
                int data = reader.read();
                while(data != -1){ // till document is ended
                    char current = (char) data; // extracts character
                    result += current; // adds char by char
                    data = reader.read();
                }
                return result;
            } catch(Exception e){
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://www.zappy.com").get(); // passing the website string to the above function
        } catch(Exception e){
            e.printStackTrace();
        }
        Log.i("Result", result);
    }
}