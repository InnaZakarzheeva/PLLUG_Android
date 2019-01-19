package course.pllug.com.sqlitedatabase.util;

import android.util.Log;

public class Logger {

    public static void i(String TAG, String message) {
        Log.i(TAG, message);
    }

    public static void e(String TAG, String message) {
        Log.e(TAG, message);
    }

    public static void d(String TAG, String message) {
        Log.d(TAG, message);
    }
}
