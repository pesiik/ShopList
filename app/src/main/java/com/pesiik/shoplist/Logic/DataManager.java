package com.pesiik.shoplist.Logic;

import android.app.Activity;
import android.widget.Toast;

import com.pesiik.shoplist.Model.Product;
import com.pesiik.shoplist.R;

import java.util.HashMap;

public class DataManager {

    private HashMap<String, Product> stringProductHashMap;
    private Activity activity;
    private static final String PATH = "products.json";


    public DataManager(Activity activity){
        this.activity = activity;
    }


    public void loadFromFile(JsonManager jsonManager){
        stringProductHashMap = jsonManager.readJsonFromPath(activity, PATH);
    }

    public void addProduct(Product product){
        if(!stringProductHashMap.containsKey(product.getName())){
            stringProductHashMap.put(product.getName(), product);
        }
        else {
            Toast.makeText(activity, R.string.add_error, Toast.LENGTH_SHORT).show();
        }

    }

    public void updateProductInfo(Product product){
        if(stringProductHashMap.containsKey(product.getName())){
            stringProductHashMap.put(product.getName(), product);
        }
    }

    public void updateToFile(JsonManager jsonManager){
        jsonManager.writeFromObjectToFile(stringProductHashMap, PATH);
    }

    public HashMap<String, Product> getStringProductMap() {
        return stringProductHashMap;
    }
}
