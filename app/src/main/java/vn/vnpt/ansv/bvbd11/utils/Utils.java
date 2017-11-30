package vn.vnpt.ansv.bvbd11.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.johnpersano.supertoasts.SuperToast;

/**
 * Created by ANSV on 11/30/2017.
 */

public class Utils {

    public static SuperToast.Animations TOAST_ANIMATION = SuperToast.Animations.FLYIN;
    public static final long TIMER_SHOW_BOTTOM_VIEW = 2000l;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
