package com.example.tungnguyen.adsrobo.Admob;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.tungnguyen.adsrobo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobInterstitialActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_interstitial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(this, getString(R.string.test_admob_appID));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.test_interstitial_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("A0A447EA9B9E9664B51231A46D0A5777").build());
        showInterstitialAds();
    }

    private void showInterstitialAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    Log.d("mInterstitialAd", "run: may co chay vao day khong");
                    Toast.makeText(AdmobInterstitialActivity.class,"May Co chay vao day khong",2);
                }
            }
        }, 10000);
    }
}
