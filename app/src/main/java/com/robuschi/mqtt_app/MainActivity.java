package com.robuschi.mqtt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    MqttHelper mqttHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            new MqttHelper(getApplicationContext()).mqttAndroidClient.setCallback(
                    new MqttCallbackExtended() {
                        @Override
                        public void connectComplete(boolean b, String s) {
                            Toast.makeText(getApplicationContext(),
                                    "MQTT connect complete",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void connectionLost(Throwable throwable) {
                            Toast.makeText(getApplicationContext(),
                                    "MQTT connection lost",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                            ((TextView)findViewById(R.id.textViewMqtt)).setText(mqttMessage.toString());
/*                            Toast.makeText(getApplicationContext(),
                                    mqttMessage.toString(),
                                    Toast.LENGTH_SHORT).show();
*/                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                            Toast.makeText(getApplicationContext(),
                                    "MQTT delivery complete",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}