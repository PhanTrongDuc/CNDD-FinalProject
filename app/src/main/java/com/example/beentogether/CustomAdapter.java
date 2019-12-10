package com.example.beentogether;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Event> {
    private Context context;
    private int resource;
    private List<Event> arrEvent;

    public CustomAdapter(Context context, int resource, ArrayList<Event> arrEvent) {
        super(context, resource, arrEvent);
        this.context = context;
        this.resource = resource;
        this.arrEvent = arrEvent;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvNote = (TextView) convertView.findViewById(R.id.tv_note);
            viewHolder.tvDay = (TextView) convertView.findViewById(R.id.tv_day);
            viewHolder.tvMonth = (TextView) convertView.findViewById(R.id.tv_month);
            viewHolder.tvYear = (TextView) convertView.findViewById(R.id.tv_year);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Event event = arrEvent.get(position);
        viewHolder.tvNote.setText(event.getNote());
        viewHolder.tvDay.setText(event.getDay());
        viewHolder.tvMonth.setText(event.getMonth());
        viewHolder.tvYear.setText(event.getYear());
        return convertView;
    }

    public class ViewHolder {
        TextView tvNote;
        TextView tvDay;
        TextView tvMonth;
        TextView tvYear;
    }

}



