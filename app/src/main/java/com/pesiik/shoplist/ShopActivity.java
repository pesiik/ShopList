package com.pesiik.shoplist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pesiik.shoplist.Logic.DataManager;
import com.pesiik.shoplist.Logic.JsonManager;
import com.pesiik.shoplist.Logic.TestDataCreator;
import com.pesiik.shoplist.Model.Product;
import com.pesiik.shoplist.Model.ProductArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopActivity extends AppCompatActivity {

    HashMap<String, Product> productHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        JsonManager jsonManager = new JsonManager();
        DataManager dataManager = new DataManager(this);
        TestDataCreator.createSomeTestData(this, jsonManager);
        dataManager.loadFromFile(jsonManager);
        productHashMap = dataManager.getStringProductMap();
        ArrayList<Product> products = new ArrayList<>(productHashMap.values());
        ProductArrayAdapter adapter = new ProductArrayAdapter(this, R.layout.product_item, products, this);
        ListView listView = findViewById(R.id.product_list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
