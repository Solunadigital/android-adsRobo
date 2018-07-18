package com.example.tungnguyen.adsrobo.Unity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tungnguyen.adsrobo.R;

public class KillAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_app);
        finishAndRemoveTask();
    }
}
