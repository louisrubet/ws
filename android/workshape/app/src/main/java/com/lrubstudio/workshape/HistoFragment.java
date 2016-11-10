package com.lrubstudio.workshape;

/**
 * Created by louis on 05/10/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoFragment extends Fragment implements DbRequest.AsyncResponse
{
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

        return view;
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString)
    {
        if (dbError == DbRequest.Error.ok)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.note_sauvee), Toast.LENGTH_LONG).show();

            // don't see button
            getView().findViewById(R.id.buttonActionNote).setVisibility(View.GONE);
            ((EditAddActivity)getActivity()).setNoteFragmentModified(false);
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
