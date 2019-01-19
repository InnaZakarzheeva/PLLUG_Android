package course.pllug.com.sqlitedatabase.db;

import course.pllug.com.sqlitedatabase.model.LogModel;

import java.util.List;

public interface LogDataInterface {
    List<LogModel> getAll();

    void addLog(LogModel model);

    void removeLog(LogModel model);

    void open();

    void close();
}
