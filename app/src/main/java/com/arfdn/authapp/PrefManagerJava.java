package com.arfdn.authapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManagerJava {

    private static final String PREFS_FILENAME = "AuthAppPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private final SharedPreferences sharedPreferences;

    private static volatile PrefManagerJava instance;

    public static PrefManagerJava getInstance(Context context) {
        if (instance == null) {
            synchronized (PrefManagerJava.class) {
                if (instance == null) {
                    instance = new PrefManagerJava(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private PrefManagerJava(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void saveUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}