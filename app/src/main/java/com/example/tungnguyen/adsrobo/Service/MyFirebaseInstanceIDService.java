package com.example.tungnguyen.adsrobo.Service;

import android.content.Intent;
import android.util.Log;

import com.example.tungnguyen.adsrobo.Admob.AdmobBannerActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobInterstitialActivity;
import com.example.tungnguyen.adsrobo.Admob.AdmobRewardActivity;
import com.example.tungnguyen.adsrobo.MainActivity;
import com.example.tungnguyen.adsrobo.Unity.KillAppActivity;
import com.example.tungnguyen.adsrobo.Unity.UnityInterstitialActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";
    private ActionType actionType = ActionType.SHOW;
    private AdmobType admobType = AdmobType.INTERSTITIAL;
    private String adsType[] = { "Banner", "Interstitial", "Reward Video" };
    private String appID = "";
    private String unitID = "";
    private String network = "";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "onMessageReceived: "+ data);
        network = data.get("network");
        appID = data.get("app_id");
        unitID = data.get("unit_id");
        int actionValue = Integer.parseInt(data.get("action"));
        actionType = ActionType.values()[actionValue];
        Log.d(TAG, "onMessageReceived: "+ data + unitID + network);
        switch (actionType) {
            case SHOW:
                break;
            case CLOSE:
                Intent killIntent = new Intent(this, KillAppActivity.class);
                killIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(killIntent);
                return;
            case INSTALL:
                //TODO: Download and install app
                break;
            case COUNT:
                //TODO: Count app still alive
                break;
        }
        String admodTypeValue = data.get("ads_type"); // ads_type = String not Int
        switch (network) {
            case "Admob":
                admodShowAds(admodTypeValue);
                return;
            case "Unity":
                String unityTypeAds = data.get("ads_type");
                String unityAppID = data.get("unity_app_id");
                switch (unityTypeAds) {
                    case "Interstitial":
                        Intent unityInterstitialIntent = new Intent(this, UnityInterstitialActivity.class);
                        unityInterstitialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        unityInterstitialIntent.putExtra("unity_app_id", unityAppID);
                        startActivity(unityInterstitialIntent);
                        return;
                    case "Reward Video":
                        return;
                    default:
                        break;
                }
                return;
            case "Facebook":
                break;
                default:
                    break;
        }

    }

    private void admodShowAds(String admodTypeValue) {
        switch (admodTypeValue) {
            case "Banner":
                Intent bannerIntent = new Intent(this, AdmobBannerActivity.class);
                bannerIntent.putExtra("appID", appID);
                bannerIntent.putExtra("unitID", unitID);
                bannerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bannerIntent.putExtra("isDevelopMode", true);
                startActivity(bannerIntent);
                return;
            case "Interstitial":
                Intent intent = new Intent(this, AdmobInterstitialActivity.class);
                intent.putExtra("appID", appID);
                intent.putExtra("unitID", unitID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("isDevelopMode",true);
                startActivity(intent);
                return;
            case "Reward Video":
                Intent rewardVideo = new Intent(this, AdmobRewardActivity.class);
                rewardVideo.putExtra("appID", appID);
                rewardVideo.putExtra("unitID", unitID);
                rewardVideo.putExtra("isDevelopMode",true);
                rewardVideo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(rewardVideo);
                return;
            default:
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