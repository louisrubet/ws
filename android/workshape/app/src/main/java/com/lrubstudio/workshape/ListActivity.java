package com.lrubstudio.workshape;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListActivity extends AppCompatActivity implements DbRequest.AsyncResponse, AdapterView.OnItemClickListener
{
    private static final String LIST_REQUEST_NAME = "list";
    private static final String PRODUCT_REQUEST_NAME = "product";

    // result of db request for products
    private ArrayList<Map> mapArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_list_title);

        // empty list management
        View empty = findViewById(R.id.textEmpty);
        ListView list = (ListView) findViewById(R.id.listProduct);
        list.setEmptyView(empty);

        // start the turning thing
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        // run asynchronous request to get products list
        new DbRequest(this, LIST_REQUEST_NAME).execute(DbProduct.buildRequestProductList(this));
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, int dbError, String dbErrorString)
    {
        try
        {
            if (requestName == LIST_REQUEST_NAME)
            {
                mapArrayList = result;

                // stop the turning thing
                findViewById(R.id.progressBar).setVisibility(View.GONE);

                if (dbError == DbRequest.DBERR_OK)
                {
                    if (mapArrayList == null)
                    {
                        // nothing in list
                    } else
                    {
                        // make a string list for the product listView
                        ArrayList<String> myStringArray = new ArrayList<String>();
                        try
                        {
                            for (int i = 0; i < mapArrayList.size(); i++)
                            {
                                Map map = mapArrayList.get(i);

                                // find field named "name" in request result
                                if (map.containsKey(DbProduct.name))
                                {
                                    String name = (String) map.get(DbProduct.name);
                                    myStringArray.add(name);
                                }
                            }
                        } catch (Exception e)
                        {
                            Toast.makeText(this, R.string.dbrequest_request_error, Toast.LENGTH_LONG).show();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);

                        // set it to the listView
                        ListView listView = (ListView) findViewById(R.id.listProduct);
                        listView.setAdapter(adapter);

                        // manage click
                        listView.setOnItemClickListener(this);
                    }
                } else
                {
                    // toast db error
                    Toast.makeText(this, DbRequest.errorCategory(this, dbError), Toast.LENGTH_LONG).show();
                }
            }
            else if (requestName == PRODUCT_REQUEST_NAME)
            {
                // stop the turning thing
                findViewById(R.id.progressBar).setVisibility(View.GONE);

                // fill db structure (use MainActivity.getLastRequestedProduct())
                MainActivity.getLastRequestedProduct().setFromMap(result);
                MainActivity.getLastRequestedProduct().setNewQrCode(false);

                // -> run edit activity
                startActivity(new Intent(this, EditAddActivity.class));
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, getResources().getString(R.string.dbrequest_request_error), Toast.LENGTH_LONG).show();
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String qr = (String)mapArrayList.get(position).get(DbProduct.qrCode);

        // keep this qr code
        MainActivity.getLastRequestedProduct().setQrCode(qr);

        // start the turning thing
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        // build request and run asynchronous request
        new DbRequest(this, PRODUCT_REQUEST_NAME).execute(DbProduct.buildRequestProductView(this, qr));
    }
}
