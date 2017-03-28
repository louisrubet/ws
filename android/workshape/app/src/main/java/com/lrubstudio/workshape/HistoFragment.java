package com.lrubstudio.workshape;

/**
 * Created by louis on 05/10/16.
 */

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoFragment extends Fragment implements DbRequest.AsyncResponse
{
    // result of db request for products
    private ArrayList<Map> mapArrayList;

    // ListAdapter item types and names
    public enum ListItemType
    {
        creation,
        update,
        update_note,
        in,
        out;
    }
    public static final String[] listItemTypes = { "", "", "", "", "" };
    public static final String[] listItemTypesNames = { "", "", "", "", "" };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // keep instance, ie be sure not to go again to onCreateView
        // in order to keep EditAddActivity.isModified state
        setRetainInstance(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_histo, container, false);

        // empty list management
        View empty = view.findViewById(R.id.textEmpty);
        ListView list = (ListView)view.findViewById(R.id.listEvent);
        list.setEmptyView(empty);

        // fill strings for events
        listItemTypesNames[ListItemType.creation.ordinal()] = getActivity().getString(R.string.histo_list_creation);
        listItemTypesNames[ListItemType.update.ordinal()] = getActivity().getString(R.string.histo_list_update);
        listItemTypesNames[ListItemType.update_note.ordinal()] = getActivity().getString(R.string.histo_list_update_note);
        listItemTypesNames[ListItemType.in.ordinal()] = getActivity().getString(R.string.histo_list_in);
        listItemTypesNames[ListItemType.out.ordinal()] = getActivity().getString(R.string.histo_list_out);

        listItemTypes[ListItemType.creation.ordinal()] = getActivity().getString(R.string.event_label_add);
        listItemTypes[ListItemType.update.ordinal()] = getActivity().getString(R.string.event_label_update);
        listItemTypes[ListItemType.update_note.ordinal()] = getActivity().getString(R.string.event_label_update_note);
        listItemTypes[ListItemType.in.ordinal()] = getActivity().getString(R.string.event_label_in);
        listItemTypes[ListItemType.out.ordinal()] = getActivity().getString(R.string.event_label_out);

        // run asynchronous request to get products list
        DbRequest.createRequest(this, null).execute(DbProduct.buildRequestEventList(getActivity(), MainActivity.getLastRequestedProduct().getQrCode()));

        return view;
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString)
    {
        ArrayList<HistoFragment.Item> valuesArray;

        try
        {
            mapArrayList = result;

            if (dbError == DbRequest.Error.ok)
            {
                if (mapArrayList == null)
                {
                    // nothing in list
                }
                else
                {
                    // make a string list for the product listView
                    valuesArray = new ArrayList<HistoFragment.Item>();
                    try
                    {
                        boolean mapIsCorrect = false;
                        if (mapArrayList.size()>0)
                        {
                            Map map = mapArrayList.get(0);
                            if (map.containsKey("qr_code") && map.containsKey("date") && map.containsKey("event")
                                    && map.containsKey("champ1") && map.containsKey("valeur1") && map.containsKey("champ2")
                                    && map.containsKey("valeur2") && map.containsKey("champ3") && map.containsKey("valeur3"))
                                mapIsCorrect = true;
                        }
                        if (mapIsCorrect)
                        {
                            for (int i = 0; i < mapArrayList.size(); i++)
                            {
                                Map map = mapArrayList.get(i);

                                //add found entry in array for later populating list
                                valuesArray.add(new HistoFragment.ListItem(
                                        (String)map.get("qr_code"), (String)map.get("date"), (String)map.get("event"),
                                        (String)map.get("champ1"), (String)map.get("valeur1"),
                                        (String)map.get("champ2"), (String)map.get("valeur2"),
                                        (String)map.get("champ3"), (String)map.get("valeur3")));
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), R.string.dbrequest_request_error, Toast.LENGTH_LONG).show();
                    }

                    // set it to the listView
                    ListView listView = (ListView)getActivity().findViewById(R.id.listEvent);

                    HistoFragment.ListAdapterDb listAdapterDb = new HistoFragment.ListAdapterDb(getActivity(), R.layout.layout_list_item, valuesArray);
                    listView.setAdapter(listAdapterDb);
                }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(), getResources().getString(R.string.dbrequest_request_error), Toast.LENGTH_LONG).show();
        }
    }

    public interface Item
    {
        public ListItemType getViewType();
        public View getView(LayoutInflater inflater, View convertView);
    }

    public class ListItem implements HistoFragment.Item
    {
        private final String qrCode;
        private final String date;
        private final String event;
        private final String champ1;
        private final String valeur1;
        private final String champ2;
        private final String valeur2;
        private final String champ3;
        private final String valeur3;
        private final ListItemType type;

        public ListItem(String qrCode, String date, String event, String champ1, String valeur1, String champ2, String valeur2, String champ3, String valeur3)
        {
            this.qrCode = qrCode;
            this.date = date;
            this.event = event;
            this.champ1 = champ1;
            this.valeur1 = valeur1;
            this.champ2 = champ2;
            this.valeur2 = valeur2;
            this.champ3 = champ3;
            this.valeur3 = valeur3;

            if (event.equals(HistoFragment.listItemTypes[ListItemType.in.ordinal()]))
                this.type = ListItemType.in;
            else if (event.equals(HistoFragment.listItemTypes[ListItemType.out.ordinal()]))
                this.type = ListItemType.out;
            else if (event.equals(HistoFragment.listItemTypes[ListItemType.creation.ordinal()]))
                this.type = ListItemType.creation;
            else if (event.equals(HistoFragment.listItemTypes[ListItemType.update_note.ordinal()]))
                this.type = ListItemType.update_note;
            else
                this.type = ListItemType.update;
        }

        public ListItemType getViewType()
        {
            return type;
        }

        @Override
        public View getView(LayoutInflater inflater, View convertView)
        {
            View view = null;

            // note convertView is not used here, because confusions were seen on BlackView BV6000 (bad view type used)
            switch(type)
            {
                case creation:
                    view = (View)inflater.inflate(R.layout.layout_histo_item_creation, null);
                    break;
                case out:
                case in:
                    view = (View)inflater.inflate(R.layout.layout_histo_item_in_out, null);
                    break;
                case update:
                case update_note:
                    view = (View)inflater.inflate(R.layout.layout_histo_item_modif, null);
                    break;
            }

            if (view != null)
            {
                // date
                TextView text = (TextView)view.findViewById(R.id.textDate);
                if (text != null)
                    text.setText(date);

                // event
                text = (TextView)view.findViewById(R.id.textTitle);
                if (text != null)
                    text.setText(HistoFragment.listItemTypesNames[type.ordinal()]);

                switch(type)
                {
                    case creation:
                        // qrCode
                        text = (TextView) view.findViewById(R.id.textListQrCode);
                        if (text != null)
                            text.setText(qrCode);

                        // content 1
                        text = (TextView) view.findViewById(R.id.textListContent1);
                        if (text != null)
                            text.setText(champ1);
                        text = (TextView) view.findViewById(R.id.textListValue1);
                        if (text != null)
                            text.setText(valeur1);
                        break;

                    case in:
                    case out:
                        // content 1
                        text = (TextView) view.findViewById(R.id.textListContent1);
                        if (text != null)
                            text.setText(champ1);
                        text = (TextView) view.findViewById(R.id.textListValue1);
                        if (text != null)
                            text.setText(valeur1);

                        // content 2
                        text = (TextView) view.findViewById(R.id.textListContent2);
                        if (text != null)
                            text.setText(champ2);
                        text = (TextView) view.findViewById(R.id.textListValue2);
                        if (text != null)
                            text.setText(valeur2);

                        // content 3
                        text = (TextView) view.findViewById(R.id.textListContent3);
                        if (text != null)
                            text.setText(champ3);
                        text = (TextView) view.findViewById(R.id.textListValue3);
                        if (text != null)
                            text.setText(valeur3);
                        break;
                }
            }

            return view;
        }
    }

    // ListAdapter
    public class ListAdapterDb extends ArrayAdapter<HistoFragment.Item>
    {
        private ArrayList<HistoFragment.Item> classes;
        private Context context;
        private LayoutInflater inflater;

        public ListAdapterDb(Context context, int textViewResourceId, ArrayList<HistoFragment.Item> classes)
        {
            super(context, textViewResourceId, classes);
            this.context = context;
            this.classes = classes;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getViewTypeCount()
        {
            return ListItemType.values().length;
        }

        public ListItemType getItemType(int position)
        {
            return getItem(position).getViewType();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getItem(position).getView(inflater, convertView);
        }
    }
}
