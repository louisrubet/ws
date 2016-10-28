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

        // start the turning thing
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        // run asynchronous request to get products list
        new DbRequest(this).execute(DbProduct.buildRequestProductList(this));
    }

    @Override
    public void dbRequestFinished(ArrayList<Map> result, int dbError, String dbErrorString)
    {
        try
        {
            mapArrayList = result;

            // stop the turning thing
            findViewById(R.id.progressBar).setVisibility(View.GONE);

            if (dbError == DbRequest.DBERR_OK)
            {
                if (mapArrayList == null)
                {
                    // nothing in list
                }
                else
                {
                    // make a string list for the product listView
                    ArrayList<String> myStringArray = new ArrayList<String>();
                    try
                    {
                        for (int i = 0; i < mapArrayList.size(); i++)
                        {
                            Map map = mapArrayList.get(i);
                            if (map.containsValue(DbProduct.name))
                            {
                                String name = (String) map.get(DbProduct.name);
                                myStringArray.add(name);
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, R.string.db_err_request, Toast.LENGTH_LONG).show();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);

                    // set it to the listView
                    ListView listView = (ListView)findViewById(R.id.listProduct);
                    listView.setAdapter(adapter);

                    // manage click
                    listView.setOnItemClickListener(this);
                }
            }
            else
            {
                // toast db error
                Toast.makeText(this, dbErrorString, Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, getResources().getString(R.string.db_err_request), Toast.LENGTH_LONG).show();
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        try
        {
            // return list item qrCode to main activity
            Intent intent = new Intent();
            intent.putExtra(DbProduct.qrCode, (String)mapArrayList.get(position).get(DbProduct.qrCode));
            setResult(RESULT_OK, intent);
            finish();
        }
        catch(Exception e)
        {
        }
    }
}
