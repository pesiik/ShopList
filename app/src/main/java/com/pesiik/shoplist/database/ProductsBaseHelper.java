package com.pesiik.shoplist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pesiik.shoplist.database.ProductDBSchema.ProductTable;

public class ProductsBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public ProductsBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ProductTable.NAME + "(" + " _id integer primary key autoincrement, " +
                ProductTable.Cols.UUID + ", " +
                ProductTable.Cols.TITLE + ", " +
                ProductTable.Cols.PRICE + ", " +
                ProductTable.Cols.COUNT + ", " +
                ProductTable.Cols.DESCRIPTION + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
