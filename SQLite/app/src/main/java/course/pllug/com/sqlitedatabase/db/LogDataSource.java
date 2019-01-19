package course.pllug.com.sqlitedatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import course.pllug.com.sqlitedatabase.model.LogModel;

import java.util.ArrayList;
import java.util.List;

public class LogDataSource implements LogDataInterface {
    // Database fields
    private SQLiteDatabase database;
    private LogSQLiteOpenHelper dbHelper;
    private String[] allColumns = {LogSQLiteOpenHelper.TB_ID,
            LogSQLiteOpenHelper.TB_MESSAGE};

    public LogDataSource(Context context) {
        dbHelper = new LogSQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }

    @Override
    public void addLog(LogModel model) {
        ContentValues values = new ContentValues();
        values.put(LogSQLiteOpenHelper.TB_MESSAGE, model.getMessage());
        values.put(LogSQLiteOpenHelper.TB_DATETIME, model.getDatetime());
        long insertId = database.insert(LogSQLiteOpenHelper.TABLE_LOG, null,
                values);
    }

    @Override
    public void removeLog(LogModel log) {
        int id = log.getId();
        System.out.println("Log deleted with id: " + id);
        database.delete(LogSQLiteOpenHelper.TABLE_LOG,
                LogSQLiteOpenHelper.TB_ID + " = " + id, null);
    }

    @Override
    public List<LogModel> getAll() {
        List<LogModel> logs = new ArrayList<LogModel>();

        Cursor cursor = database.query(LogSQLiteOpenHelper.TABLE_LOG,
                allColumns, null, null, null, null, null);

        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                LogModel log = cursorToLog(cursor);
                logs.add(log);
                cursor.moveToNext();
            }
        }
        // make sure to close the cursor
        cursor.close();
        return logs;
    }

    private LogModel cursorToLog(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(LogSQLiteOpenHelper.TB_ID));
        String message = cursor.getString(cursor.getColumnIndex(LogSQLiteOpenHelper.TB_MESSAGE));

        LogModel item = new LogModel();
        item.setId(id);
        item.setMessage(message);
        return item;
    }
}
