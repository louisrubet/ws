package com.lrubstudio.workshape;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
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
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // refresh list each time activity comes in front

        // empty list management
        View empty = findViewById(R.id.textEmpty);
        ListView list = (ListView) findViewById(R.id.listProduct);
        list.setEmptyView(empty);

        // start the turning thing
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        // run asynchronous request to get products list
        DbRequest.createRequest(this, LIST_REQUEST_NAME).execute(DbProduct.buildRequestProductList(this));
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString)
    {
        ArrayList<Item> valuesArray;

        try
        {
            if (requestName == LIST_REQUEST_NAME)
            {
                mapArrayList = result;

                // stop the turning thing
                findViewById(R.id.progressBar).setVisibility(View.GONE);

                if (dbError == DbRequest.Error.ok)
                {
                    if (mapArrayList == null)
                    {
                        // nothing in list
                    }
                    else
                    {
                        // make a string list for the product listView
                        valuesArray = new ArrayList<Item>();
                        valuesArray.add(new ListItem(ListAdapterDb.HEADER_ITEM, "", getString(R.string.name), getString(R.string.longueur_actuelle_short), ""));
                        try
                        {
                            for (int i = 0; i < mapArrayList.size(); i++)
                            {
                                Map map = mapArrayList.get(i);

                                // find field named "name" in request result
                                if (map.containsKey(DbProduct.qrCode) && map.containsKey(DbProduct.name) && map.containsKey(DbProduct.longueurActuelle))
                                {
                                    String qrCode = (String) map.get(DbProduct.qrCode);
                                    String name = (String) map.get(DbProduct.name);
                                    String longueur = (String) map.get(DbProduct.longueurActuelle);
                                    String lieu_actuel = (String) map.get(DbProduct.lieuActuel);
                                    valuesArray.add(new ListItem(ListAdapterDb.LIST_ITEM, qrCode, name, longueur, lieu_actuel));
                                }
                            }
                        } catch (Exception e)
                        {
                            Toast.makeText(this, R.string.dbrequest_request_error, Toast.LENGTH_LONG).show();
                        }

                        // set it to the listView
                        ListView listView = (ListView)findViewById(R.id.listProduct);

                        ListAdapterDb listAdapterDb = new ListAdapterDb(this, R.layout.layout_list_item, valuesArray);
                        listView.setAdapter(listAdapterDb);

                        // manage click
                        listView.setOnItemClickListener(this);
                    }
                }
                else
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
        ListItem item = (ListItem)parent.getItemAtPosition(position);

        if (item != null && item.type == ListAdapterDb.LIST_ITEM)
        {
            String qr = item.qrCode;

            // keep this qr code
            MainActivity.getLastRequestedProduct().setQrCode(qr);

            // start the turning thing
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

            // build request and run asynchronous request
            DbRequest.createRequest(this, PRODUCT_REQUEST_NAME).execute(DbProduct.buildRequestProductView(this, qr));
        }
    }

    // ListAdapter item
    public interface Item
    {
        public int getViewType();
        public View getView(LayoutInflater inflater, View convertView);
    }

    public class ListItem implements Item
    {
        final String qrCode;
        final String name;
        final String longueur;
        final String lieu_actuel;
        final int type;

        public ListItem(int type, String qrCode, String name, String longueur, String lieu_actuel)
        {
            this.qrCode = qrCode;
            this.name = name;
            this.longueur = longueur;
            this.lieu_actuel = lieu_actuel;
            this.type = type;
        }

        @Override
        public int getViewType()
        {
            return type;
        }

        @Override
        public View getView(LayoutInflater inflater, View convertView)
        {
            View view = null;
            if (convertView == null)
            {
                if (type == ListAdapterDb.LIST_ITEM)
                    view = (View)inflater.inflate(R.layout.layout_list_item, null);
                else if (type == ListAdapterDb.HEADER_ITEM)
                    view = (View)inflater.inflate(R.layout.layout_list_header_item, null);
            }
            else
            {
                view = convertView;
            }

            if (view != null)
            {
                // in red if out
                boolean red = (qrCode != null && qrCode != "") && (lieu_actuel == null || lieu_actuel == "");
                TextView text = (TextView) view.findViewById(R.id.textListContent2);
                text.setText(name);

                if (type == ListAdapterDb.LIST_ITEM)
                {
                    if (red)
                        text.setTextColor(getResources().getColor(R.color.colorAccent));
                    else
                        text.setTextColor(getResources().getColor(R.color.colorListTextView));
                }

                text = (TextView) view.findViewById(R.id.textListContent1);
                text.setText(longueur);

                if (type == ListAdapterDb.LIST_ITEM)
                {
                    if (red)
                        text.setTextColor(getResources().getColor(R.color.colorAccent));
                    else
                        text.setTextColor(getResources().getColor(R.color.colorListTextView));
                }
            }

            return view;
        }
    }

    // ListAdapter
    public class ListAdapterDb extends ArrayAdapter<Item>
    {
        public static final int LIST_ITEM = 0;
        public static final int HEADER_ITEM = 1;
        public static final int ITEM_TYPE_NB = 2;
        private ArrayList<Item> classes;
        private Context context;
        private LayoutInflater inflater;

        public ListAdapterDb(Context context, int textViewResourceId, ArrayList<Item> classes)
        {
            super(context, textViewResourceId, classes);
            this.context = context;
            this.classes = classes;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getViewTypeCount()
        {
            return ITEM_TYPE_NB;
        }

        @Override
        public int getItemViewType(int position)
        {
            return getItem(position).getViewType();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            int rowType = getItemViewType(position);

            if ((convertView == null) || (convertView.getTag() != (Integer)rowType))
            {
                // inflate view
                switch (rowType)
                {
                    case LIST_ITEM:
                        convertView = inflater.inflate(R.layout.layout_list_item, null);
                        convertView.setTag(Integer.valueOf(rowType));
                        break;
                    case HEADER_ITEM:
                        convertView = inflater.inflate(R.layout.layout_list_header_item, null);
                        convertView.setTag(Integer.valueOf(rowType));
                        break;
                }
            }

            if (convertView != null)
            {
                // in red if out
                ListItem item = (ListItem)getItem(position);
                boolean red = (item.qrCode != null && item.qrCode != "") && (item.lieu_actuel == null || item.lieu_actuel == "");
                TextView text = (TextView) convertView.findViewById(R.id.textListContent2);
                text.setText(item.name);

                if (rowType == LIST_ITEM)
                {
                    if (red)
                        text.setTextColor(getResources().getColor(R.color.colorAccent));
                    else
                        text.setTextColor(getResources().getColor(R.color.colorListTextView));
                }

                text = (TextView) convertView.findViewById(R.id.textListContent1);
                text.setText(item.longueur);
                if (rowType == LIST_ITEM)
                {
                    if (red)
                        text.setTextColor(getResources().getColor(R.color.colorAccent));
                    else
                        text.setTextColor(getResources().getColor(R.color.colorListTextView));
                }
            }

            return convertView;
        }
    }
}
