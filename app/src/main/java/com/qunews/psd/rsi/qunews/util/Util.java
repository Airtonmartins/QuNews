package com.qunews.psd.rsi.qunews.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by airton on 29/07/17.
 */

public class Util {

    public static String pegarMac(Context contexto){
        WifiManager manager = (WifiManager) contexto.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String mac = info.getMacAddress();
        return mac;
    }
}
