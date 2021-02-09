 package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> { // class which will do the download work

        @Override
        protected String doInBackground(String... strings) {
            Log.i("URL", strings[0]); // printing the passed string with log
            return "Done"; // this is the result which we put in result variable 
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://www.zappycode.com").get(); // passing the website string to the above function
        } catch(Exception e){
            e.printStackTrace();
        }
        Log.i("Result", result);
    }
}