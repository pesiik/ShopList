package com.pesiik.shoplist.Logic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.pesiik.shoplist.Model.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataCreator {

    private static final String TAG = "TestDataCreator";
    private static final String PATH = "products.json";

    public static void createSomeTestData(Activity activity, JsonManager jsonManager){
        Product androidPhone = new Product(0, "Xiaomi Note 3 Pro", 10000.50, "Крутой, хоть и Китайский!", 5);
        Product iosPhone = new Product(1, "IPhone SE", 20000d, "Если важно яблочко в соц сетях. Включил и пользуешь (почти)", 2);
        Product windowsPhone = new Product(2, "Nokia Lumia 520", 6000d, "А помните? Был такой.", 15);

        HashMap<String, Product> products = new HashMap<>();
        products.put(androidPhone.getName(),androidPhone);
        products.put(iosPhone.getName(),iosPhone);
        products.put(windowsPhone.getName(),windowsPhone);



        FileOutputStream outputStream = null;
        try {
            outputStream = activity.openFileOutput(PATH, Context.MODE_APPEND);
            String jsonString = jsonManager.getStringFromObject(products);
            outputStream.write(jsonString.getBytes());
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(outputStream!=null)
                    outputStream.close();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
