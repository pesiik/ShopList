package com.pesiik.shoplist.Logic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesiik.shoplist.Model.Product;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String TAG = "JsonManager";


    public HashMap<String, Product> readJsonFromPath(Activity activity, String path){
        List<Product> products = new ArrayList<Product>();
        HashMap<String, Product> integerProductHashMap = new HashMap<>();

        try {
            InputStream inputStream = activity.openFileInput(path);

            try {
                products = mapper.readValue(inputStream,
                        mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class)); //right here todo
                for (Product p: products) {
                    integerProductHashMap.put(p.getName(), p);
                }
            } catch (JsonParseException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            } catch (JsonMappingException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage()); //no such file in directory todo
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return integerProductHashMap;
    }

    public HashMap<String, Product> readJsonFromApi(URL path){
        List<Product> products = new ArrayList<Product>();
        HashMap<String, Product> integerProductHashMap = new HashMap<>();
        try {
            products = mapper.readValue(path, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class));
            for (Product p: products) {
                integerProductHashMap.put(p.getName(), p);
            }
        } catch (JsonParseException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }
        return integerProductHashMap;
    }

    public String getStringFromObject(HashMap<String, Product>  products){
        String value = "";
        try {
            value = mapper.writeValueAsString(products.values());
        }
        catch (JsonProcessingException e){
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }
        return value;
    }

    public void writeFromObjectToFile(HashMap<String, Product> products, String path){
        File file = new File(path);
        for (Product product: products.values()) {
            try {
                mapper.writeValue(file, product);
            } catch (JsonProcessingException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
