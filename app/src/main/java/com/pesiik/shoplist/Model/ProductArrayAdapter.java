package com.pesiik.shoplist.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pesiik.shoplist.R;

import java.util.ArrayList;
import java.util.List;

public class ProductArrayAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> products;

    public ProductArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Product> objects) {
        super(context, resource, textViewResourceId, objects);
        products = new ArrayList<>(objects);

    }


    @Override
    public int getCount() {
        return products.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View productView = convertView;

        TextView price = (TextView) productView.findViewById(R.id.price);
        String priceString = products.get(position).getPrice().toString();
        price.setText(priceString);

        TextView name = (TextView) productView.findViewById(R.id.name);
        String nameString = products.get(position).getName();
        name.setText(nameString);


        TextView count = (TextView) productView.findViewById(R.id.count_products);
        Integer countInt = (Integer) products.get(position).getCount();
        count.setText(countInt);


        TextView description = (TextView) productView.findViewById(R.id.description);
        String descriptionString = products.get(position).getDescription();
        description.setText(descriptionString);

        return productView;
    }
}
