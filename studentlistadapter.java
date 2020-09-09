package com.example.final1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class studentlistadapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<student> list;

    public studentlistadapter(Context context, int layout, ArrayList<student> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView name,rollno,email,password;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.name = (TextView) row.findViewById(R.id.name);
            holder.rollno = (TextView) row.findViewById(R.id.reg);
            holder.email = (TextView) row.findViewById(R.id.email);
            holder.password = (TextView) row.findViewById(R.id.password);
            row.setTag(holder);
        } else {
            holder = (studentlistadapter.ViewHolder) row.getTag();
        }

        student food = list.get(position);

        holder.name.setText(food.getName());
        holder.rollno.setText(food.getReg());
        holder.email.setText(food.getEmail());
        holder.password.setText(food.getPassword());

        return row;
    }
}

