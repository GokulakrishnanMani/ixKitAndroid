package com.inx.customreusablecomponent.permissionmanager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by gokul on 18/5/18.
 */

public class PermissonManager {


    public PermissonManager() {

    }

    /*
    * Check if version is marshmallow and above.
    * Used in deciding to ask runtime permission
    * */
    public static boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    private static boolean shouldAskPermission(Context context, String permission) {
        if (shouldAskPermission()) {
            int permissionResult = ActivityCompat.checkSelfPermission(context, permission);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(23)
    public static void checkPermission(Activity context, String permission, PermissionAskListener listener) {
/*
        * If permission is not granted
        * */
        if (shouldAskPermission(context, permission)) {
/*
            * If permission denied previously
            * */
            if (context.shouldShowRequestPermissionRationale(permission)) {
                listener.onPermissionPreviouslyDenied();
            } else {
                /*
                * Permission request disabled previously or very first time requesting the permission
                * */
                if (PermissionPrefernceManger.isFirstTimeAskingPermission(context, permission)) {
                    PermissionPrefernceManger.setFirstTimeAskingPermission(context, permission, false);
                    listener.onPermissionRequired();
                } else {
                    /*
                    * Handle the feature without permission or ask user to manually allow permission
                    * */
                    listener.onPermissionDisabled();
                }
            }


        } else {
            listener.onPermissionGranted();
        }
    }


    /*
        * Callback on various cases on checking permission
        *
        * 1.  Below M, runtime permission not needed. In that case onPermissionGranted() would be called.
        *     If permission is already granted, onPermissionGranted() would be called.
        *
        * 2.  Above M, if the permission is being asked first time onPermissionRequired() would be called.
        *
        * 3.  Above M, if the permission is previously asked but not granted, onPermissionPreviouslyDenied()
        *     would be called.
        *
        * 4.  Above M, if the permission is disabled by device policy or the user checked "Never ask again"
        *     check box on previous request permission, onPermissionDisabled() would be called.
        * */
    public interface PermissionAskListener {
        /*
                * Callback to ask permission
                * */
        void onPermissionRequired();

        /*
                * Callback on permission denied
                * */
        void onPermissionPreviouslyDenied();

        /*
                * Callback on permission "Never show again" checked and denied
                * */
        void onPermissionDisabled();

        /*
                * Callback on permission granted
                * */
        void onPermissionGranted();
    }
}
