package com.pesiik.shoplist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.pesiik.shoplist.Model.Product;

import java.util.UUID;

public class ProductCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct(){
        String uuidString = getString(getColumnIndex(ProductDBSchema.ProductTable.Cols.UUID));
        String title = getString(getColumnIndex(ProductDBSchema.ProductTable.Cols.TITLE));
        String countString = getString(getColumnIndex(ProductDBSchema.ProductTable.Cols.COUNT));
        String priceString = getString(getColumnIndex(ProductDBSchema.ProductTable.Cols.PRICE));
        String description = getString(getColumnIndex(ProductDBSchema.ProductTable.Cols.DESCRIPTION));

        Double price = Double.parseDouble(priceString);
        int count = Integer.parseInt(countString);

        return new Product(UUID.fromString(uuidString), title, price, description, count);
    }
}
