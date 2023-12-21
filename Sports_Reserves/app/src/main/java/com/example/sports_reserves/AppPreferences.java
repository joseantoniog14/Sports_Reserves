package com.example.sports_reserves;

import android.content.SharedPreferences;

public class AppPreferences {
    private SharedPreferences preferences;
    public AppPreferences(SharedPreferences preferences){
        this.preferences=preferences;
    }
    public SharedPreferences getSharedPreferences(){
        return preferences;
    }
    public String getUsername(){
        return preferences.getString("username"," ");
    }
    public void setUsername(String username){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("username",username);
        editor.apply();
    }
}
