package com.example.tungnguyen.adsrobo;


import com.example.tungnguyen.adsrobo.Admob.AdmobBannerActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.d("Menu", "Setting button clicked!");
            return true;
        }
        switch (id) {
            case R.id.action_settings:
                ///var intent = Intent()
                break;
            case R.id.admob_baner:
                Intent bannerIntent = new Intent(this, AdmobBannerActivity.class);
                startActivity(bannerIntent);
            case R.id.admob_intertitial:
                break;
            case R.id.admob_rewardVideo:
                break;

                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
