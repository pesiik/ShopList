package com.pesiik.shoplist.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductManager {
    private static ProductManager sProductManager;

    private List<Product> mProducts;

    public static ProductManager get(Context context){
        if(sProductManager == null){
            sProductManager = new ProductManager(context);
        }
        return sProductManager;
    }

    private ProductManager(Context context){
            mProducts = new ArrayList<>();
            Product xiaomiProduct = new Product("Xiaomi Note 3 Pro", 10000.50, "Крутой, хоть и Китайский!", 5);
            mProducts.add(xiaomiProduct);
            Product iosPhone = new Product("IPhone SE", 20000d, "Если важно яблочко в соц сетях. Включил и пользуешь (почти)", 2);
            mProducts.add(iosPhone);
            Product windowsPhone = new Product("Nokia Lumia 520", 6000d, "А помните? Был такой.", 15);
            mProducts.add(windowsPhone);
            Product nokia5100 = new Product("Nokia 5100", 600d, "Однажды он спас мне жизнь. Я смог убежать от стаффорда", 1);
            mProducts.add(nokia5100);
            Product meizuPhone = new Product("Meizu MX6", 16000d, "Маме такой купил", 100);
            mProducts.add(meizuPhone);
    }

    public Product getProduct(UUID id){
        for(Product product : mProducts){
            if(product.getId().equals(id))
                return product;
        }
        return null;
    }

    public void AddProduct(Product product){
        mProducts.add(product);
    }

    public void removeProduct(UUID uuid){
        for (Product product : mProducts){
            if(product.getId() == uuid){
                mProducts.remove(product);
                break;
            }
        }
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
