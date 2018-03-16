package com.example.android.product.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamil on 2018-01-18.
 */

public class ProductDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = ProductDbHelper.class.getSimpleName();


    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PRODUCTS_TABLE =
                "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " ("

                + ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PRODUCER + " TEXT NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " REAL NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + " REAL NOT NULL);";

        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
