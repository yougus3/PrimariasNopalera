package com.gms.primariasnopalera.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.gms.primariasnopalera.R;
import com.google.android.material.snackbar.Snackbar;

public class Functions {
    public void snackBar(View view, String mensaje, Activity activity){
        Snackbar.make(view, mensaje, Toast.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(activity, R.color.golden)).show();
    }
    public boolean isConnected(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}
