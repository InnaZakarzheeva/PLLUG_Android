package course.pllug.com.sqlitedatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import course.pllug.com.sqlitedatabase.util.Logger;

public class LogSQLiteOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "log.db";

    private final static int DB_VERSION = 1;

    public final static String TAG = "LogSQLiteOpenHelper";


    public final static String AUTHORITY = "com.teslyuk.android.androidtutorial.db.log";
    public final static String BASE_PATH = "log_database";

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //- - - - - - - TABLE NAMES - - - - - - - - - - - - - - - - -
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public final static String TABLE_LOG = "LOG";

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //- - - - - - - TABLE URI - - - - - - - - - - - - - - - - -
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public final static Uri LOG_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_LOG);

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //- - - - - - - TABLE IDS - - - - - - - - - - - - - - - - -
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public final static int TABLE_LOG_ID = 1;

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //- - - - - - - TABLE FIELDS- - - - - - - - - - - - - - - -
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public static final String TB_ID = "ID";

    //TABLE_LOG
    public static final String TB_MESSAGE = "MESSAGE";
    public static final String TB_EXCEPTION = "EXCEPTION";
    public static final String TB_DATETIME = "DATETIME";

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //- - - - - - - CREATE TABLE- - - - - - - - - - - - - - - -
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private final static String CREATE_TABLE_LOG = "CREATE TABLE " + TABLE_LOG + "\n" +
            "                 ( " + TB_ID + " integer primary key\n" +
            "                 , " + TB_MESSAGE + " varchar not NULL\n" +
            "                 , " + TB_EXCEPTION + " varchar NULL\n" +
            "                 , " + TB_DATETIME + " varchar not NULL)";

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public LogSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public LogSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.i(TAG, "onCreate");
        db.execSQL(CREATE_TABLE_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.i(TAG, "onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);

        onCreate(db);
    }
}