package com.harshilshah.petproject;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;

/**
 *  Class used to manage power status and battery low counts of device.
 */
class BatteryStatus {
    private Context context;
    private MainActivity mainActivityInstance;

    BatteryStatus(Context context){
        this.context = context;
        this.mainActivityInstance = MainActivity.getInstance();
    }

    /**
     * Handles battery low action and update low battery counts.
     */
    void actionBatteryLow(){
        int count = this.readCount();
        this.writeCount(++count);

        // Update counts if Main activity is active.
        if(mainActivityInstance != null){
            mainActivityInstance.updateBatteryLowCounts(count);
        }
    }

    /**
     * Update text views when power status is changed
     * @param action    string containing received action.
     */
    void actionPowerStatusChanged(final String action){
        boolean isCharging;

        // Update charging status if Main activity is active.
        if(mainActivityInstance != null) {
            isCharging = action.equals(Intent.ACTION_POWER_CONNECTED);
            mainActivityInstance.updateChargingStatus(isCharging);
        }
    }

    /**
     * Read battery low count from shared preference storage.
     * @return int count
     */
    int readCount() {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.default_shared_preference_name)
                , Context.MODE_PRIVATE);
        return sharedPref.getInt(context.getString(R.string.battery_low_count_key), 0);
    }

    /**
     * Store low battery counts in share preference.
     * @param val   value to store.
     */
    private void writeCount(final int val) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.default_shared_preference_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(R.string.battery_low_count_key), val);
        editor.apply();
    }

    /**
     * Check current charging status of the device. This function is used when App instance created
     * for the first time
     * @return  status of charging in boolean.
     */
    boolean isCharging(){
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatusIntentReceiver = context.registerReceiver(null, iFilter);

        int status = batteryStatusIntentReceiver.getIntExtra(BatteryManager.EXTRA_STATUS
                ,-1);

        return status == BatteryManager.BATTERY_PLUGGED_AC
                || status == BatteryManager.BATTERY_PLUGGED_USB;
    }
}
