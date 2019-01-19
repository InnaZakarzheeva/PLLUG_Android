package course.pllug.com.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import course.pllug.com.sqlitedatabase.adapter.LogListAdapter;
import course.pllug.com.sqlitedatabase.db.LogContentProviderWrapper;
import course.pllug.com.sqlitedatabase.db.LogDataInterface;
import course.pllug.com.sqlitedatabase.db.LogDataSource;
import course.pllug.com.sqlitedatabase.model.LogModel;
import course.pllug.com.sqlitedatabase.util.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        LogListAdapter.LogListAdapterEventListener {

    private static final String TAG = "DatabaseStorage";

    private ListView list;
    private EditText text;
    private Button addBtn;

    private List<LogModel> logs;
    private LogListAdapter adapter;

    private LogDataInterface dataSource;
    private boolean useContentProvider = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (useContentProvider) {
            dataSource = new LogContentProviderWrapper(this);
        } else {
            dataSource = new LogDataSource(this);
        }
    }

    private void initView() {
        list = findViewById(R.id.list);
        text = findViewById(R.id.enter);
        addBtn = findViewById(R.id.add);

        addBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
        updateLogList();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    private void createLog() {
        Logger.d(TAG, "createLog");
        String logName = Calendar.getInstance().getTimeInMillis() + " millis";
        String tx = text.getText().toString();

        LogModel model = new LogModel(tx, logName);
        dataSource.addLog(model);
    }

    private void updateLogList() {
        Logger.d(TAG, "updateLogList");
        logs = dataSource.getAll();

        if (logs == null) {
            logs = new ArrayList<>();
        }

        for (int i = 0; i < logs.size(); i++) {
            Logger.d(TAG, "i:" + i + " name: " + logs.get(i).getMessage()
                    + " datetime: " + logs.get(i).getDatetime());
        }

        if (adapter == null) {
            adapter = new LogListAdapter(this, logs);
            adapter.addListener(this);
            list.setAdapter(adapter);
        } else {
            adapter.onDataUpdate(logs);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                createLog();
                updateLogList();
                break;
        }
    }

    @Override
    public void removeLog(int position) {
        if (logs != null && logs.get(position) != null) {
            dataSource.removeLog(logs.get(position));
        }
        updateLogList();
    }

}

