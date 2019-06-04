package com.harshilshah.petproject;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
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
    public void updateChargingStatus(final boolean isCharging){

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

        textView.setTextColor(getColor(R.color.green));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f);
    }

    /**
     * Function to reset non active charging state.
     * @param textView  Text view which need to be reset.
     */
    private void resetTextView(@NonNull final TextView textView)
    {
        textView.setTextColor(getColor(R.color.red));
        textView.setTypeface(Typeface.DEFAULT);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
    }

    /**
     * Function to update text view containing low counts information.
     * @param count count to show in text view.
     */
    public void updateBatteryLowCounts(final int count){
        TextView BatteryLowCountsTextView = findViewById(R.id.low_counts);
        BatteryLowCountsTextView.setText(String.valueOf(count));
    }
}

