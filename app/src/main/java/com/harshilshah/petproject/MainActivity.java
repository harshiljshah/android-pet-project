package com.harshilshah.petproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import lombok.NonNull;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivityInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityInstance = this;

        BatteryStatusController batteryStatusController = new BatteryStatusController(getApplicationContext());
        updateBatteryLowCounts(batteryStatusController.readBatteryLowCount());
        updateChargingStatus(batteryStatusController.isCharging());

    }

    /**
     * Get mainActivityInstance of the activity.
     * @return returns mainActivityInstance of the activity
     */
    static MainActivity getMainActivityInstance(){
        return mainActivityInstance;
    }

    /**
     * Function to uddate text views representing charging status.
     * @param isCharging    boolean variable to represent charging or not charging.
     */
    void updateChargingStatus(final boolean isCharging){

        TextView chargingTextView = findViewById(R.id.charging_text_view);
        TextView notChargingTextView = findViewById(R.id.not_charging_text_view);

        if(isCharging){
            // Set charging text view
            setTextView(chargingTextView);

            // Reset not charging text view.
            resetTextView(notChargingTextView);
        }
        else{
            // Set not charging text view.
            setTextView(notChargingTextView);

            // Reset charging text view.
            resetTextView(chargingTextView);
        }
    }

    /**
     * Function to set active charging state.
     * @param textView  Text view which needs to be activate.
     */
    private void setTextView(@NonNull final TextView textView)
    {
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * Function to reset non active charging state.
     * @param textView  Text view which need to be reset.
     */
    private void resetTextView(@NonNull final TextView textView)
    {
        textView.setVisibility(View.GONE);
    }

    /**
     * Function to update text view containing low counts information.
     * @param count count to show in text view.
     */
    void updateBatteryLowCounts(final int count){
        TextView BatteryLowCountsTextView = findViewById(R.id.low_counts);
        BatteryLowCountsTextView.setText(String.valueOf(count));
    }
}

