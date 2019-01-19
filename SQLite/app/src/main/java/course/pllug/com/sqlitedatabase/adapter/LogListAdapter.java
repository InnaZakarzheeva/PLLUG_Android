package course.pllug.com.sqlitedatabase.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import course.pllug.com.sqlitedatabase.R;
import course.pllug.com.sqlitedatabase.model.LogModel;

import java.util.List;

public class LogListAdapter extends BaseAdapter {

    public interface LogListAdapterEventListener {
        public void removeLog(int position);
    }

    private final String TAG = getClass().getSimpleName();

    private List<LogModel> list;
    private Context context;
    LogListAdapterEventListener listener;
    private int selectedItem = -1;

    public LogListAdapter(Context context, List<LogModel> list) {
        this.context = context;
        this.list = list;
        Log.d(TAG, "items count: " + list.size());
    }

    public void addListener(LogListAdapterEventListener listener) {
        this.listener = listener;
    }

    static class ViewHolder {
        protected TextView name;
        protected ImageView delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            final ViewHolder viewHolder = new ViewHolder();
            view = inflator.inflate(R.layout.delete, null);
            viewHolder.name = (TextView) view.findViewById(R.id.log_name);
            viewHolder.delete = (ImageView) view.findViewById(R.id.image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder.name != null) {
            holder.name.setText(list.get(position).getMessage());
        }

        if (holder.delete != null) {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.removeLog(position);
                    }
                }
            });
        }

        if (view == null) {
            Log.d(TAG, "NULL VIEW position:" + position);
        }
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void onDataUpdate(List<LogModel> newData) {
        list.clear();
        list.addAll(newData);
        notifyDataSetChanged();
    }
}