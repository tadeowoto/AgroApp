package com.example.agroapp.lib;

import android.content.Context;
import android.content.SharedPreferences;

public class Services {

    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("tokenapp.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("tokenapp.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }
}
