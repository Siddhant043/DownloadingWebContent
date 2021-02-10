 package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

 public class MainActivity extends AppCompatActivity {

     ImageView imageView;

    public void downloadButton(View view){
        Log.i("Info", "Button is working");
        ImageDownloader obj = new ImageDownloader();
        try {
            Bitmap myImage = obj.execute("https://lh3.googleusercontent.com/proxy/69ZQNEQJBJ1pVBzwBKE1YoUVPGDw8dG2PP7WbfV2ukWLuoB75-ObyXCqfRYecdsvLGEC_TbOH9w6zZuztDv_oc0t2K-C5yKWlJf6aMQGG7h227enbwE8J1AglkUPeqLXWg").get();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{
        HttpURLConnection connection;

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(connection.getInputStream()); // buffered in imp and add network access permission
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                connection.disconnect();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }
 }
