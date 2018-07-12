package com.example.tungnguyen.adsrobo.Admob;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.tungnguyen.adsrobo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class AdmobRewardActivity extends AppCompatActivity {
    private RewardedVideoAd mRewardedVideoAd;
    private String appID = "";
    private String unitID = "";
    private Boolean isDevMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_reward);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        /// Get bundle
        appID = isDevMode ? getString(R.string.test_admob_appID) : getIntent().getStringExtra("appID");
        unitID = isDevMode ? getString(R.string.test_reward_video_unit_id) : getIntent().getStringExtra("unitID");
        AdRequest request = isDevMode ?
                new AdRequest.Builder().addTestDevice("A0A447EA9B9E9664B51231A46D0A5777").build() :
                new AdRequest.Builder().build();
        MobileAds.initialize(this, appID);
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(unitID, request);

        showInterstitialAds();
    }

    private void showInterstitialAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        }, 10000);
    }
}
