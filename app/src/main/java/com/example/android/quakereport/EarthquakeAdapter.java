package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter {

    ViewHolder viewHolder;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakeArrayList) {
        super(context, 0, earthquakeArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.magnitude = convertView.findViewById(R.id.magnitude);
            viewHolder.place = convertView.findViewById(R.id.place);
            viewHolder.date = convertView.findViewById(R.id.date);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Earthquake earthquake = (Earthquake) getItem(position);

        viewHolder.magnitude.setText(earthquake.getMag());
        viewHolder.place.setText(earthquake.getPlace());
        viewHolder.date.setText(earthquake.getDate());

        return convertView;
    }

    private class  ViewHolder{
        TextView magnitude;
        TextView place;
        TextView date;
    }
}
