package com.example.tungnguyen.adsrobo.Admob;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tungnguyen.adsrobo.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobInterstitialActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private Boolean isDevelopMode = true;
    public static String appID;
    public static String interstitialUnitID;
    AdRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_interstitial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        // Get data from intent
        String applicationID = getIntent().getStringExtra("appID");
        String unitID = getIntent().getStringExtra("unitID");
        isDevelopMode = getIntent().getBooleanExtra("isDevelopMode", true);
        mInterstitialAd = new InterstitialAd(this);
        appID = isDevelopMode ?
                getString(R.string.test_admob_appID) : applicationID ;

        interstitialUnitID = isDevelopMode ?
                getString(R.string.test_interstitial_unit_id): unitID ;

        configInterstitialAds(appID, interstitialUnitID);
    }

    private void configInterstitialAds(String appID, String interstitialUnitID){
        MobileAds.initialize(this, appID);
        mInterstitialAd.setAdUnitId(interstitialUnitID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        showInterstitialAds();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                reloadInterstitial();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }

    private void showInterstitialAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    reloadInterstitial();
                }
            }
        }, 10000);
    }

    private void reloadInterstitial() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
