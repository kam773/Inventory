package com.example.android.product;


import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


import com.example.android.product.data.ProductContract;

/**
 * Created by Kamil on 2018-01-18.
 */

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    int quantity = 0;
    private EditText mNameEditText;
    private EditText mProducerEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;
    private Uri mCurrentProductUri;
    private boolean mProductHasChanged = false;

    private static final int EXISTING_PRODUCT_LOADER = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        if (mCurrentProductUri == null) {

            setTitle(getString(R.string.editor_activity_title_new_product));


            invalidateOptionsMenu();
        } else {

            setTitle(getString(R.string.editor_activity_title_edit_product));

        }


        mNameEditText = (EditText) findViewById(R.id.name_input);
        mProducerEditText = (EditText) findViewById(R.id.producer_input);
        mPriceEditText = (EditText) findViewById(R.id.price_input);
        mQuantityEditText = (EditText) findViewById(R.id.quantity_input);




        FloatingActionButton fabPlus = (FloatingActionButton) findViewById(R.id.fab_plus);

        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 50) {

                    Toast.makeText(EditorActivity.this, "You can't insert more than 50 items", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity = quantity + 1;
                displayQuantity(quantity);
            }
        });

        FloatingActionButton fabMinus = (FloatingActionButton) findViewById(R.id.fab_minus);

        fabMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1) {

                    Toast.makeText(EditorActivity.this, "You can't insert less than 1 item", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity = quantity - 1;
                displayQuantity(quantity);
            }
        });

        getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
    }


    private void displayQuantity(int numberOfItems) {
        TextView quantityEditText = (TextView) findViewById(R.id.quantity_input);

        quantityEditText.setText("" + numberOfItems);
    }

    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mProductHasChanged) {
            super.onBackPressed();

        }

    }

    private void saveProduct() {

        String nameString = mNameEditText.getText().toString().trim();
        String producerString = mProducerEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();


        if (mCurrentProductUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(producerString) &&
                TextUtils.isEmpty(quantityString) && TextUtils.isEmpty(priceString)) {

            return;
        }

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRODUCER, producerString);

        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);

        int price = 0;
        if (!TextUtils.isEmpty(priceString)) {
            price = Integer.parseInt(quantityString);
        }
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price);

        if (mCurrentProductUri == null) {

            Uri newUri = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);

            if (newUri == null) {

                Toast.makeText(this, getString(R.string.editor_insert_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_insert_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);


            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:
                saveProduct();

                finish();
                return true;

            case R.id.action_delete:


                return true;

            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                return true;


        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}


