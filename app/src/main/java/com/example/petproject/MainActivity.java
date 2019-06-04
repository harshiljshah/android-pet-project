package com.example.petproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import lombok.NonNull;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        BatteryStatus batteryStatus = new BatteryStatus(getApplicationContext());
        updateBatteryLowCounts(batteryStatus.readCount());
        updateChargingStatus(batteryStatus.isCharging());

    }

    /**
     * Get instance of the activity.
     * @return returns instance of the activity
     */
    public static MainActivity getInstance(){
        return instance;
    }

    /**
     * Function to uddate text views representing charging status.
     * @param isCharging    boolean variable to represent charging or not charging.
     */
    public void updateChargingStatus(@NonNull final boolean isCharging){

        TextView chargingTextView = findViewById(R.id.charging_text_view);
        TextView notChargingTextView = findViewById(R.id.not_charging_text_view);

        if(isCharging){
            chargingTextView.setTextColor(getColor(R.color.green));
            notChargingTextView.setTextColor(getColor(R.color.red));
        }
        else{
            chargingTextView.setTextColor(getColor(R.color.red));
            notChargingTextView.setTextColor(getColor(R.color.green));
        }
    }

    /**
     * Function to update text view containing low counts information.
     * @param count count to show in text view.
     */
    public void updateBatteryLowCounts(@NonNull final int count){
        TextView BatteryLowCountsTextView = findViewById(R.id.low_counts);
        BatteryLowCountsTextView.setText(String.valueOf(count));
    }
}

