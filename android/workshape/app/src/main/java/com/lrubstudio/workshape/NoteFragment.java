package com.lrubstudio.workshape;

/**
 * Created by louis on 05/10/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class NoteFragment extends DataFragment implements View.OnClickListener, DbRequest.AsyncResponse
{
    // widget ids to be filled with db fields
    int[] _ids = new int [] { R.id.editNote };

    // db fields to populate widgets
    String[] _fields = new String [] { DbProduct.note };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        // fill with db field
        MainActivity.getLastRequestedProduct().fillFragmentFromFields(view, _ids, _fields );

        // record Edits to watch for modification
        ViewWatcher.AddMultipleWidgetsToWatcher(this, view, _ids);

        // set save button state
        if (((EditAddActivity)getActivity()).isNoteFragmentModified())
            view.findViewById(R.id.buttonActionNote).setVisibility(View.VISIBLE);
        else
            view.findViewById(R.id.buttonActionNote).setVisibility(View.GONE);

        // manage button
        view.findViewById(R.id.buttonActionNote).setOnClickListener(this);

        return view;
    }

    @Override
    public void wasModified()
    {
        // user modified an Edit
        // show or hide modified button
        getView().findViewById(R.id.buttonActionNote).setVisibility(View.VISIBLE);
        ((EditAddActivity)getActivity()).setNoteFragmentModified(true);
    }

    @Override
    public void onClick(View view)
    {
        // build and run request
        String qrCode = MainActivity.getLastRequestedProduct().getQrCode();
        if (qrCode.length() > 0)
        {
            String note = ((EditText)getView().findViewById(R.id.editNote)).getText().toString();
            DbRequest.createCommand(this, null).execute(DbProduct.buildRequestProductUpdateNote(getActivity(), qrCode, DbProduct.timeNowToString(getActivity()), note));
        }
        else
        {
            // unknown qr code !
            Toast.makeText(getActivity(), getActivity().getString(R.string.qrcode_inconnu), Toast.LENGTH_LONG).show();
        }
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

            // not modified anymore
            ((EditAddActivity)getActivity()).setNoteFragmentModified(false);

            // correct db data
            MainActivity.getLastRequestedProduct().fillFieldsFromFragment(getView(),  _ids, _fields);

            // record Edits to watch for modification
            ViewWatcher.AddMultipleWidgetsToWatcher(this, getView(), _ids);
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
