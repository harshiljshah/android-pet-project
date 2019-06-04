package com.harshilshah.petproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import lombok.NonNull;


/**
 * Class to receive Power Plugged, unplugged and Battery low actions.
 */
public class BatteryStatusReceiver extends BroadcastReceiver {

    /**
     * Directing different received actions by broadcast receiver.
     * @param context   context of application
     * @param intent    Intent of receiver
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        BatteryStatusController batteryStatusController = new BatteryStatusController(context);
        @NonNull String action = intent.getAction();

        // Handling battery low action
        if(action.equals(Intent.ACTION_BATTERY_LOW))
            batteryStatusController.actionBatteryLow();

        // Handling Power connected and disconnected action.
        else if(action.equals(Intent.ACTION_POWER_CONNECTED)
                || action.equals(Intent.ACTION_POWER_DISCONNECTED))
            batteryStatusController.actionPowerStatusChanged(action);
    }
}
