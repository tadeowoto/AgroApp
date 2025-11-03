package com.example.agroapp.lib;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Services {

    private static final SimpleDateFormat DATE_FORMAT_DISPLAY =
            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    private static final SimpleDateFormat DATE_FORMAT_API =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
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

    public static Date parseFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.isEmpty()) {
            return null;
        }

        try {

            return DATE_FORMAT_DISPLAY.parse(fechaStr);
        } catch (ParseException e1) {
            try {

                return DATE_FORMAT_API.parse(fechaStr);
            } catch (ParseException e2) {
                try {

                    return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(fechaStr);
                } catch (ParseException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
    }


    public static String formatFechaApi(Date fecha) {
        if (fecha == null) return null;
        return DATE_FORMAT_API.format(fecha);
    }


    public static String formatFechaDisplay(Date fecha) {
        if (fecha == null) return "-";
        return DATE_FORMAT_DISPLAY.format(fecha);
    }
}
