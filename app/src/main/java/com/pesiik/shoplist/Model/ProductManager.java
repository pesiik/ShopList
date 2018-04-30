package com.pesiik.shoplist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pesiik.shoplist.database.ProductCursorWrapper;
import com.pesiik.shoplist.database.ProductsBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pesiik.shoplist.database.ProductDBSchema.*;

public class ProductManager {
    private static ProductManager sProductManager;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ProductManager get(Context context){
        if(sProductManager == null){
            sProductManager = new ProductManager(context);
        }

        return sProductManager;
    }

    private ProductManager(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ProductsBaseHelper(mContext).getWritableDatabase();

    }

    public Product getProduct(UUID id){
        ProductCursorWrapper cursor = queryProducts(ProductTable.Cols.UUID + " = ?", new String[]{id.toString()});
        try {
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToNext();
            return cursor.getProduct();
        }
        finally {
            cursor.close();
        }

    }

    public void AddProduct(Product product){
        ContentValues values = getContentValues(product);

        mDatabase.insert(ProductTable.NAME, null, values);
    }

    public void updateProduct(Product product){
        String uuidString = product.getId().toString();
        ContentValues values = getContentValues(product);

        mDatabase.update(ProductTable.NAME, values,
                ProductTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private ProductCursorWrapper queryProducts(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(ProductTable.NAME, null,whereClause,whereArgs,
                null, null, null);
        return new ProductCursorWrapper(cursor);
    }

    public void removeProduct(Product product){

        mDatabase.delete(ProductTable.NAME,ProductTable.Cols.UUID + " = ?", new String[]{product.getId().toString()});
    }

    private static ContentValues getContentValues(Product product){
        ContentValues values = new ContentValues();
        values.put(ProductTable.Cols.UUID, product.getId().toString());
        values.put(ProductTable.Cols.TITLE, product.getName());
        values.put(ProductTable.Cols.COUNT, product.getCount());
        values.put(ProductTable.Cols.PRICE, product.getPrice().toString());
        values.put(ProductTable.Cols.DESCRIPTION, product.getDescription());
        return values;
    }

    public List<Product> getProducts()
    {
        List<Product> products = new ArrayList<>();

        ProductCursorWrapper cursor = queryProducts(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                products.add(cursor.getProduct());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return products;
    }
}
