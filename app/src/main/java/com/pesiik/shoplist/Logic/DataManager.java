package com.pesiik.shoplist.Logic;

import android.app.Activity;
import android.widget.Toast;

import com.pesiik.shoplist.Model.Product;
import com.pesiik.shoplist.R;

import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private HashMap<String, Product> integerProductMap;
    private Activity activity;
    private static final String PATH = "products.json";


    public DataManager(Activity activity){
        this.activity = activity;
    }


    public void loadFromFile(JsonManager jsonManager){
        integerProductMap = jsonManager.readJsonFromPath(PATH);
    }

    public void addProduct(Product product){
        if(!integerProductMap.containsKey(product.getName())){
            integerProductMap.put(product.getName(), product);
        }
        else {
            Toast.makeText(activity, R.string.add_error, Toast.LENGTH_SHORT).show();
        }

    }

    public void updateProductInfo(Product product){
        if(integerProductMap.containsKey(product.getName())){
            integerProductMap.put(product.getName(), product);
        }
    }

    public void updateToFile(JsonManager jsonManager){
        jsonManager.writeFromObjectToFile(integerProductMap, PATH);
    }

    public Map<String, Product> getIntegerProductMap() {
        return integerProductMap;
    }
}
