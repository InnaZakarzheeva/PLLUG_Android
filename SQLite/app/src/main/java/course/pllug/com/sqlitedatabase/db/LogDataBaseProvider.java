package course.pllug.com.sqlitedatabase.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import course.pllug.com.sqlitedatabase.util.Logger;

public class LogDataBaseProvider extends ContentProvider {

    public LogSQLiteOpenHelper _dbHelper;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(LogSQLiteOpenHelper.AUTHORITY, LogSQLiteOpenHelper.TABLE_LOG, LogSQLiteOpenHelper.TABLE_LOG_ID);
    }

    public String getType(Uri url) {
        String tb = null;
        int match = sURIMatcher.match(url);
        switch (match) {
            case LogSQLiteOpenHelper.TABLE_LOG_ID:
                tb = LogSQLiteOpenHelper.TABLE_LOG;
                break;
        }
        return tb;
    }

    @Override
    public boolean onCreate() {
        _dbHelper = new LogSQLiteOpenHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Logger.i("Provider", "delete");
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        int rowsDeleted = _db.delete(getType(uri), selection, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Logger.i("Provider", "insert");
        long id = 0;
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        id = _db.insert(getType(uri), null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(LogSQLiteOpenHelper.BASE_PATH + "/" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Logger.i("Provider", "query");
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(getType(uri));
        SQLiteDatabase _db = _dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(_db, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Logger.i("Provider", "update");
        int rowsUpdated = 0;
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        rowsUpdated = _db.update(getType(uri), values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
