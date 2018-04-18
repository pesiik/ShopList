package com.pesiik.shoplist.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pesiik.shoplist.R;

import java.util.ArrayList;
import java.util.List;

public class ProductArrayAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> products;
    Activity activity;

    public ProductArrayAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects, Activity activity) {
        super(context, resource, objects);
        products = new ArrayList<>(objects);

        this.activity = activity;

    }


    @Override
    public int getCount() {
        return products.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View productView = convertView;

        if(productView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            productView = inflater.inflate(R.layout.product_item, parent, false);
        }

        TextView price = (TextView) productView.findViewById(R.id.price);
        String priceString = products.get(position).getPrice().toString();
        price.setText(priceString);

        TextView name = (TextView) productView.findViewById(R.id.name);
        String nameString = products.get(position).getName();
        name.setText(nameString);


        TextView count = (TextView) productView.findViewById(R.id.count_products);
        int countInt = products.get(position).getCount();
        count.setText(String.valueOf(countInt));


        TextView description = (TextView) productView.findViewById(R.id.description);
        String descriptionString = products.get(position).getDescription();
        description.setText(descriptionString);

        return productView;
    }
}
