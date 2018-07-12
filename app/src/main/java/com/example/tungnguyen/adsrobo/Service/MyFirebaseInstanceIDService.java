package com.example.tungnguyen.adsrobo.Service;

import android.content.Intent;
import android.util.Log;

import com.example.tungnguyen.adsrobo.Admob.AdmobBannerActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobInterstitialActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobRewardActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";
    private ActionType actionType = ActionType.SHOW;
    private AdmobType admobType = AdmobType.INTERSTITIAL;
    private String appID = "";
    private String unitID = "";
    private String network = "";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: Co chay vao day khogn");
        Map<String, String> data = remoteMessage.getData();
        appID = data.get("app_id");
        unitID = data.get("unit_id");
        network = data.get("network");
        int actionValue = Integer.parseInt(data.get("action"));
        actionType = ActionType.values()[actionValue];
        int admodTypeValue = Integer.parseInt(data.get("ads_type"));
        admobType = AdmobType.values()[admodTypeValue];
        switch (admobType) {
            case BANNER:
                Intent bannerIntent = new Intent(this, AdmobBannerActivity.class);
                bannerIntent.putExtra("appID", appID);
                bannerIntent.putExtra("unitID", unitID);
                bannerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bannerIntent.putExtra("isDevelopMode", true);
                startActivity(bannerIntent);
            case INTERSTITIAL:
                Intent intent = new Intent(this, AdmobInterstitialActivity.class);
                intent.putExtra("appID", appID);
                intent.putExtra("unitID", unitID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("isDevelopMode",true);
                startActivity(intent);
            case REWARDVIDEO:
                Intent rewardVideo = new Intent(this, AdmobRewardActivity.class);
                rewardVideo.putExtra("appID", appID);
                rewardVideo.putExtra("unitID", unitID);
                rewardVideo.putExtra("isDevelopMode",true);
                rewardVideo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(rewardVideo);
        }

        switch (actionType) {
            case SHOW:
                Intent intent = new Intent(this, AdmobInterstitialActivity.class);
                intent.putExtra("appID", appID);
                intent.putExtra("unitID", unitID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            case CLOSE:
                //TODO: Kill app
                break;
            case INSTALL:
                //TODO: Download and install app
                break;
            case COUNT:
                //TODO: Count app still alive
                break;
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "onNewToken: "+ s);
        Log.d(TAG, "onNewToken: " + FirebaseInstanceId.getInstance().getToken());
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    private void killApp() {
        // TODO: Kill app
    }

    private void countNumberDeviceStillLive() {
        //TODO: Call API here
    }

    private void downloadAndInstallApp(String appURL) {
        //TODO: Download apk file and install programmatically
    }

}