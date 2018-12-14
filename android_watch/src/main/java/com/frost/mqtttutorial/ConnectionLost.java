package com.frost.mqtttutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;

public class ConnectionLost extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_lost);
    }

    public void backHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
