package com.inx.customreusablecomponent.permissionmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by gokul on 18/5/18.
 */

public class PermissionPrefernceManger {

    private static final String PREF_NAME = "com.ionixx.pref";
//    private static Context activityContext;
//    private static PermissionPrefernceManger preferenceManager;
//    private SharedPreferences preferences;
//
//    private SharedPreferences.Editor editor;
//
//    public PermissionPrefernceManger(Context context){
//        activityContext = context;
//        preferences = this.getPreferences();
//    }
//    public SharedPreferences getPreferences(){
//        if(preferences == null){
//            return  preferences = activityContext.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
//        }
//        return preferences;
//    }
//    public static synchronized PermissionPrefernceManger getInstance(Context context){
//        if (preferenceManager == null){
//            preferenceManager = new PermissionPrefernceManger(context);
//        }
//        return preferenceManager;
//    }
//
//    public String getStringContent (String key) {
//        return  preferences.getString(key,null);
//    }
//
//    public Integer getIntegerContent (String key) {
//        return  preferences.getInt(key,0);
//    }
//
//    public Boolean getBooleanContent (String key) {
//        return  preferences.getBoolean(key,false);
//    }
//
//    public Boolean putStringContent (String key, String value){
//        editor = preferences.edit();
//        editor.putString(key,value);
//        return editor.commit();
//    }
//
//    public Boolean putIntegerContent (String key, int value){
//        editor = preferences.edit();
//        editor.putInt(key,value);
//        return editor.commit();
//    }
//
//    public Boolean putBooleanContent(String key, Boolean value){
//        editor = preferences.edit();
//        editor.putBoolean(key,value);
//        return editor.commit();
//    }
//
//    public Boolean clearPreference (){
//        editor = preferences.edit();
//        editor.clear();
//        return editor.commit();
//    }

    /**
     * Set the flag to true/false up on whether the permission has already requested or not.
     * @param context
     * @param permission
     * @param isFirstTime
     */
    public static void setFirstTimeAskingPermission(Context context, String permission, boolean isFirstTime){
        SharedPreferences sharedPreference = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply();
    }

    /**
     * To check whether this application asking for the permission at very first time.
     * @param context
     * @param permission
     * @return
     */
    public static boolean isFirstTimeAskingPermission(Context context, String permission){
        return context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).getBoolean(permission, true);
    }
}
