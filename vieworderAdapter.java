package com.example.final1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class vieworderAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<order> list;

    public vieworderAdapter(Context context, int layout, ArrayList<order> list) {
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
        TextView name, price,quantity,total,status,email;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.name = (TextView) row.findViewById(R.id.name);
            holder.price = (TextView) row.findViewById(R.id.price);
            holder.quantity = (TextView) row.findViewById(R.id.quantity);
            holder.total = (TextView) row.findViewById(R.id.total);
            holder.status = (TextView) row.findViewById(R.id.status);
            holder.email = (TextView) row.findViewById(R.id.email);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        order food = list.get(position);

        holder.name.setText(food.getName());
        holder.price.setText(food.getPrice());
        holder.quantity.setText(food.getQuantity());
        holder.total.setText(food.getTotal());
        holder.status.setText(food.getStaus());
        holder.email.setText(food.getEmail());

        return row;
    }
}