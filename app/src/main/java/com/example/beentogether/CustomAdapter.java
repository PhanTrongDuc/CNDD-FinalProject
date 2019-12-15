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
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder.tvNote= convertView.findViewById(R.id.tv_note);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Event event = arrEvent.get(position);
        viewHolder.tvNote.setText(event.getNote()+event.toString());
        return convertView;
    }

    public class ViewHolder {
        TextView tvNote;
    }
}



