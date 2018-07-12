package com.example.tungnguyen.adsrobo.Admob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tungnguyen.adsrobo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdmobBannerActivity extends AppCompatActivity {
    private AdView mAdView;
    private String appId;
    private String bannerUnitId;
    private Boolean isDevelopMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_banner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        appId = isDevelopMode ?
                getString(R.string.test_admob_appID) :
                getIntent().getStringExtra("appID");
        bannerUnitId = isDevelopMode ?
                getString(R.string.test_banner_ad_unit_id) :
                getIntent().getStringExtra("unitID");
        configBanner(appId, bannerUnitId);
    }

    private void configBanner(String appID, String unitId) {
        MobileAds.initialize(this, appID);
        mAdView.setAdUnitId(unitId);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
