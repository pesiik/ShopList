package com.pesiik.shoplist.Logic;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesiik.shoplist.Model.Product;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String TAG = "JsonManager";


    public HashMap<String, Product> readJsonFromPath(String path){
        List<Product> products = new ArrayList<Product>();
        HashMap<String, Product> integerProductHashMap = new HashMap<>();
        try {
            products = mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class));
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
        StringBuffer stringBuffer = new StringBuffer();
        for (Product product: products.values()) {
            try {
                String value = mapper.writeValueAsString(product);
                stringBuffer.append(value);
            } catch (JsonProcessingException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
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
