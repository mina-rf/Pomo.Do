package com.sharif.PomoDo;

/**
 * Created by mina on 7/11/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<TaskHolder> textitems;

    public CustomAdapter(Context context, List<TaskHolder> list) {
        super();
        this.textitems = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return textitems.size();
    }

    @Override
    public Object getItem(int position) {
        return textitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return textitems.indexOf(getItem(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list, null);

            holder.taskName = (TextView) convertView.findViewById(R.id.task_name_inlist);
            holder.targetAndDone = (TextView) convertView.findViewById(R.id.task_target_done);
            holder.start = (Button) convertView.findViewById(R.id.task_start);
            holder.label = (Button) convertView.findViewById(R.id.task_label);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.taskName.setText(textitems.get(position).taskName);
        holder.targetAndDone.setText(textitems.get(position).done + " / " + textitems.get(position).target);
        holder.label.setBackgroundColor(textitems.get(position).color);
        return convertView;
    }

    class ViewHolder {
        TextView taskName;
        TextView targetAndDone;
        Button start;
        Button label;
    }
}
