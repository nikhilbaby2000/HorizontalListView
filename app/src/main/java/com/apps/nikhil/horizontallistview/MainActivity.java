package com.apps.nikhil.horizontallistview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;






public class MainActivity extends ActionBarActivity {

    //NetworkImageView networkImageView;
    RequestQueue queue;
    //HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        queue = Volley.newRequestQueue(this);
        String[] urlss = {"http://www.wpclipart.com/cartoon/signs/more_signs/What_word_bubble.png",
                "http://www.hdwallpapersinn.com/wp-content/uploads/2014/07/baby-girl-blue-eyes-beautiful-images-desktop-hd-wallappers.jpg",
                "https://raw.githubusercontent.com/lucasr/twoway-view/master/images/sample.png"};
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv1);
        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            LruCache<String,Bitmap> mCache = new LruCache<String,Bitmap>(10);
            @Override
            public Bitmap getBitmap(String s) {
                return mCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                mCache.put(s,bitmap);
            }
        });

        //final NetworkImageView networkImageView =new NetworkImageView(this);
//        networkImageView.setImageUrl(urlss[1],imageLoader);

          LinearLayout linearLayout12 = (LinearLayout) findViewById(R.id.linearLayout1);


        for (int i = 0; i< urlss.length; i++) {
            NetworkImageView networkImageView = new NetworkImageView(MainActivity.this);
            networkImageView.setImageUrl(urlss[i],imageLoader);

            //networkImageView.setVisibility(View.VISIBLE);
            //networkImageView.setBackgroundColor(Color.GREEN);
            networkImageView.setDefaultImageResId(R.drawable.ic_launcher2);
            networkImageView.setAdjustViewBounds(true);
            networkImageView.setId(networkImageView.generateViewId());
            //networkImageView.setBackgroundResource(R.drawable.ic_launcher);
            networkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    Toast.makeText(getApplicationContext(),"Clicked Item no="+id,Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout12.addView(networkImageView,i);
            linearLayout12.setDividerPadding(5);

            linearLayout12.requestLayout();
        }


        Button LocationButton = (Button) findViewById(R.id.getLocationButton);
        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LocationsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);




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
}
