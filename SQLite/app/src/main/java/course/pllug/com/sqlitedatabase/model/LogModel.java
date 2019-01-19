package course.pllug.com.sqlitedatabase.model;

import java.util.Calendar;


public class LogModel {
    int id;
    String message;
    String exception;
    String datetime;

    public LogModel() {
        datetime = String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    public LogModel(String message, String exception) {
        this.message = message;
        this.exception = exception;
        datetime = String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
