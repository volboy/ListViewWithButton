package com.example.listviewwithbutton;

import android.content.Context;
import android.icu.text.UFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, int resource, ArrayList<Product> product) {
        super(context, resource, product);
        this.productList=product;
        this.layout=resource;
        this.inflater=LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product product = productList.get(position);

        viewHolder.nameView.setText(product.getName());
        viewHolder.countView.setText(formatValue(product.getCount(), product.getUnit()));

        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = product.getCount() - 1;
                if (count < 0) count = 0;
                product.setCount(count);
                viewHolder.countView.setText(formatValue(count, product.getUnit()));
            }
        });
        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=product.getCount()+1;
                product.setCount(count);
                viewHolder.countView.setText(formatValue(count,product.getUnit()));
            }
        });
        return convertView;
    }

    private  String formatValue(int count, String unit){
        return String.valueOf(count)+ " "+unit;
    }
    private class ViewHolder{
        final Button addButton, removeButton;
        final TextView nameView, countView;
        ViewHolder(View view){
            addButton=view.findViewById(R.id.addButton);
            removeButton=view.findViewById(R.id.removeButton);
            nameView=view.findViewById(R.id.nameView);
            countView=view.findViewById(R.id.countView);
        }
    }
}
