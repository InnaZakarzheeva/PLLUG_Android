package course.pllug.com.sqlitedatabase.db;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import course.pllug.com.sqlitedatabase.model.LogModel;

import java.util.ArrayList;
import java.util.List;

public class LogContentProviderWrapper implements LogDataInterface {

    private ContentResolver contentResolver;

    public LogContentProviderWrapper(Activity activity) {
        contentResolver = activity.getContentResolver();
    }

    @Override
    public List<LogModel> getAll() {
        String[] projection = new String[]{
                LogSQLiteOpenHelper.TB_ID,
                LogSQLiteOpenHelper.TB_MESSAGE,
                LogSQLiteOpenHelper.TB_EXCEPTION,
                LogSQLiteOpenHelper.TB_DATETIME,};
        Cursor data = contentResolver.query(LogSQLiteOpenHelper.LOG_URI, projection, null, null, null);
        List<LogModel> logItems = null;

        if (data != null && data.getCount() != 0) {
            logItems = new ArrayList<LogModel>();
            if (data.moveToFirst()) {
                do {
                    int id = data.getInt(data.getColumnIndex(LogSQLiteOpenHelper.TB_ID));
                    String message = data.getString(data.getColumnIndex(LogSQLiteOpenHelper.TB_MESSAGE));
                    String exception = data.getString(data.getColumnIndex(LogSQLiteOpenHelper.TB_EXCEPTION));
                    String datetime = data.getString(data.getColumnIndex(LogSQLiteOpenHelper.TB_DATETIME));

                    LogModel item = new LogModel();
                    item.setId(id);
                    item.setMessage(message);
                    item.setException(exception);
                    item.setDatetime(datetime);

                    logItems.add(item);
                } while (data.moveToNext());
            }
        }
        data.close();
        return logItems;
    }

    private List<LogModel> getLogsTime() {
        String[] projection = new String[]{LogSQLiteOpenHelper.TB_ID, LogSQLiteOpenHelper.TB_DATETIME,};
        Cursor data = contentResolver.query(LogSQLiteOpenHelper.LOG_URI, projection, null, null, null);
        List<LogModel> logItems = null;

        if (data.getCount() != 0) {
            logItems = new ArrayList<LogModel>();
            if (data.moveToFirst()) {
                do {
                    int id = data.getInt(data.getColumnIndex(LogSQLiteOpenHelper.TB_ID));
                    String datetime = data.getString(data.getColumnIndex(LogSQLiteOpenHelper.TB_DATETIME));

                    LogModel item = new LogModel();
                    item.setId(id);
                    item.setDatetime(datetime);

                    logItems.add(item);
                } while (data.moveToNext());
            }
        }
        data.close();
        return logItems;
    }

    @Override
    public void addLog(LogModel errorLog) {
        ContentValues values = new ContentValues();
        values.put(LogSQLiteOpenHelper.TB_MESSAGE, errorLog.getMessage());
        values.put(LogSQLiteOpenHelper.TB_EXCEPTION, errorLog.getException());
        values.put(LogSQLiteOpenHelper.TB_DATETIME, errorLog.getDatetime());

        contentResolver.insert(LogSQLiteOpenHelper.LOG_URI, values);
    }

    @Override
    public void removeLog(LogModel model) {
        removeLogById(model.getId());
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    private void cleanLogs() {
        contentResolver.delete(LogSQLiteOpenHelper.LOG_URI, null, null);
    }

    private void cleanLogsByDate(int id) {
        String selection = LogSQLiteOpenHelper.TB_ID + " <= " + id;
        contentResolver.delete(LogSQLiteOpenHelper.LOG_URI, selection, null);
    }

    private void removeLogById(int id) {
        String selection = LogSQLiteOpenHelper.TB_ID + " == " + id;
        contentResolver.delete(LogSQLiteOpenHelper.LOG_URI, selection, null);
    }
}

