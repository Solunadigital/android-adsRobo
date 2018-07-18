package com.example.tungnguyen.adsrobo.Admob;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tungnguyen.adsrobo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class AdmobRewardActivity extends AppCompatActivity {
    private RewardedVideoAd mRewardedVideoAd;
    private String appID = "" ;
    private String unitID = "";
    private Boolean isDevMode = false ;
    private String TAG = "DEbug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_reward);
        appID = getIntent().getStringExtra("appID");
        unitID = getIntent().getStringExtra("unitID");

        AdRequest request =new AdRequest.Builder().build();
        MobileAds.initialize(this, appID);
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(unitID, request);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                mRewardedVideoAd.show();
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                final Handler closeHandle = new Handler();
                closeHandle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        AdmobRewardActivity.this.finish();
//                        System.exit(0);
                        finishAndRemoveTask();
                    }
                }, 2000);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });

        /// Nếu không load được thì sau 1 phút nó sẽ tự đóng
        final Handler closeHandle = new Handler();
        closeHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
//                        AdmobRewardActivity.this.finish();
//                        System.exit(0);
                if (!mRewardedVideoAd.isLoaded()){
                    finishAndRemoveTask();
                }
            }
        }, 60000);
    }

    private void showInterstitialAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                    final Handler closeHandle = new Handler();
                    closeHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AdmobRewardActivity.this.finish();
                            System.exit(0);
                        }
                    }, 5000);
                }
            }
        }, 10000);
    }
}
