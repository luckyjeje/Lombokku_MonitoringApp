package com.example.dalannamabapadanputeradanrohkudusamin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.System;

public class Util {
    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy", new Locale("in", "ID")).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("in", "ID")).format(System.currentTimeMillis());
    }
}
