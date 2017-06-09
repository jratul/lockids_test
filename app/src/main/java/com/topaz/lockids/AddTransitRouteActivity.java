package com.topaz.lockids;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddTransitRouteActivity extends AppCompatActivity {

    private LinearLayout imgLinear;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transit_route);

        imgLinear = (LinearLayout) findViewById(R.id.imgLinear);
        loadBtn = (Button) findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewImage();
            }
        });

    }

    private void loadNewImage() {
        new DownloadImageTask(imgLinear).execute("http://cfile5.uf.tistory.com/image/224E8F3E57148F7A0E43B5");
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        LinearLayout imgLinear;

        public DownloadImageTask(LinearLayout imgLinear) {
            this.imgLinear = imgLinear;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlStr = urls[0];
            Log.d("AddTransit", urlStr);
            Bitmap image = null;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream in = connection.getInputStream();
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return image;
        }

        protected void onPostExecute(Bitmap result) {
            ImageView imgView = new ImageView(AddTransitRouteActivity.this);
            imgView.setImageBitmap(result);
            imgView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imgLinear.addView(imgView);
        }
    }
}
