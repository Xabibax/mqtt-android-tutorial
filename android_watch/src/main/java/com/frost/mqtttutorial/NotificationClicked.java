package com.frost.mqtttutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.wearable.activity.WearableActivity;

public class NotificationClicked extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_clicked);
    }
}
