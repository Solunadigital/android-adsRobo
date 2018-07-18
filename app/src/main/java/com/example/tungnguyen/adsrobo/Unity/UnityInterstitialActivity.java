package com.example.tungnguyen.adsrobo.Unity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tungnguyen.adsrobo.Admob.AdmobRewardActivity;
import com.example.tungnguyen.adsrobo.R;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.ads.metadata.MetaData;
import com.unity3d.ads.metadata.PlayerMetaData;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.SdkProperties;
import com.unity3d.ads.webview.WebView;

import static android.content.ContentValues.TAG;

public class UnityInterstitialActivity extends AppCompatActivity {
    final private String defaultGameId = "14851";
    private String interstitialPlacementId;
    private String incentivizedPlacementId;

    private static int ordinal = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_interstitial);
        final UnityInterstitialActivity self = this;
        final UnityAdsListener unityAdsListener = new UnityAdsListener();

        UnityAds.setListener(unityAdsListener);
        UnityAds.initialize(self,defaultGameId,unityAdsListener,false);

    }

    /* LISTENER */

    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(final String zoneId) {
            DeviceLog.debug("onUnityAdsReady: " + zoneId);
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (zoneId) {
                        case "defaultVideoAndPictureZone":
                            interstitialPlacementId = zoneId;
                            UnityAds.show(UnityInterstitialActivity.this,interstitialPlacementId);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finishAndRemoveTask();
                                }
                            }, 35000);
                            return;
                    }
                }
            });
            toast("Ready", zoneId);
        }

        @Override
        public void onUnityAdsStart(String zoneId) {
            DeviceLog.debug("onUnityAdsStart: " + zoneId);
            toast("Start", zoneId);

        }

        @Override
        public void onUnityAdsFinish(String zoneId, UnityAds.FinishState result) {
            DeviceLog.debug("onUnityAdsFinish: " + zoneId + " - " + result);
            toast("Finish", zoneId + " " + result);
            finishAndRemoveTask();
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            DeviceLog.debug("onUnityAdsError: " + error + " - " + message);
            toast("Error", error + " " + message);
            finishAndRemoveTask();
        }

        private void toast(String callback, String msg) {
            Toast.makeText(getApplicationContext(), callback + ": " + msg, Toast.LENGTH_SHORT).show();
        }
    }
}