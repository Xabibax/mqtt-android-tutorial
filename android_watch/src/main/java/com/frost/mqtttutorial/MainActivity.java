package com.frost.mqtttutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import helpers.MqttHelper;

public class MainActivity extends WearableActivity {

    private MqttHelper mqttHelper;

    private TextView dataReceived;
    private ProgressBar connection;

    private MyNotificationsManager my_notif_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataReceived = (TextView) findViewById(R.id.dataReceived);
        connection = (ProgressBar) findViewById(R.id.progressBar);

        my_notif_manager = new MyNotificationsManager(this);

        startMqtt();
    }

    private void startMqtt(){
        mqttHelper = new MqttHelper(getApplicationContext());
        dataReceived.setText("Connection en cours.");
        connection.setVisibility(View.VISIBLE);
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug","Connected");

                dataReceived.setText("Connection réussie.");
                connection.setVisibility(View.INVISIBLE);
            }

            @Override
            public void connectionLost(Throwable throwable) {
                goToConnectionlost();
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug",mqttMessage.toString());

                dataReceived.setText(mqttMessage.toString());
                my_notif_manager
                        .setChannel()
                        .setNotification(mqttMessage.toString())
                        .sendNotification()
                ;
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
    public void goToConnectionlost() {
        startActivity(new Intent(this, ConnectionLost.class));
    }
    public void sendNotification(View view) {
        my_notif_manager
                .setChannel()
                .setNotification("Une notification.")
                .sendNotification()
        ;
    }
}
