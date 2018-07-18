package com.example.tungnguyen.adsrobo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tungnguyen.adsrobo.Admob.AdmobBannerActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobInterstitialActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobIntertitialVideoActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobRewardActivity;
import com.example.tungnguyen.adsrobo.Unity.UnityInterstitialActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        FirebaseMessaging.getInstance().subscribeToTopic("admob");
        Log.d("Tag firebase token", "onNewToken: " + FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.admob_baner:
                Intent bannerIntent = new Intent(this, AdmobBannerActivity.class);
                startActivity(bannerIntent);
                return true;
            case R.id.admob_intertitial:
                Intent interstitialIntent = new Intent(this, AdmobInterstitialActivity.class);
                startActivity(interstitialIntent);
                return true;
            case R.id.admob_intertitial_video:
                Intent intertitialVideoIntent = new Intent(this, AdmobIntertitialVideoActivity.class);
                startActivity(intertitialVideoIntent);
                return true;
            case R.id.admob_rewardVideo:
                Intent rewardVideoIntent = new Intent(this, UnityInterstitialActivity.class);
                startActivity(rewardVideoIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
