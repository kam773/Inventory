package com.example.android.product.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.product.R;

/**
 * Created by Kamil on 2018-03-02.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView producerTextView = (TextView) view.findViewById(R.id.product_producer);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_tv);
        TextView priceTextView = (TextView) view.findViewById(R.id.price_tv);

        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int producerColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRODUCER);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);

        int id = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry._ID));

        String productName = cursor.getString(nameColumnIndex);
        String producerName = cursor.getString(producerColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        String price = cursor.getString(priceColumnIndex);


        nameTextView.setText(productName);
        producerTextView.setText(producerName);
        quantityTextView.setText(quantity);
        priceTextView.setText(price);

    }
}
