package com.zmt.thenews.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import javax.inject.Inject;

/**
 * Created by lin min phyo on 2/21/18.
 */

public class NetworkUtils {
  Context context;

  @Inject public NetworkUtils(Context context) {
    this.context = context;
  }

  public String getCurrentSSID() {
    String SSID = "";
    ConnectivityManager connectionManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    // If connection manager exists
    if (connectionManager != null) {
      NetworkInfo networkInfo = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

      // Network is connected
      if (networkInfo.isConnected()) {
        final WifiManager wifiManager =
            (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // If wifi manager exists
        if (wifiManager != null) {
          WifiInfo connectionInfo = wifiManager.getConnectionInfo();

          // If connection info exists
          if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
            SSID = connectionInfo.getBSSID();
          }
        }
      }
    }
    return SSID;
  }

  @SuppressWarnings("deprecation")
  public static boolean isInternetAvailable(Context context) {
    ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      Network network = conMgr.getActiveNetwork();
      NetworkCapabilities networkCapabilities = conMgr.getNetworkCapabilities(network);
      if (networkCapabilities != null) {
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
      } else return false;
    } else {
      // below API Level 23
      return conMgr.getActiveNetworkInfo() != null
              && conMgr.getActiveNetworkInfo().isAvailable()
              && conMgr.getActiveNetworkInfo().isConnected();
    }
  }
}
