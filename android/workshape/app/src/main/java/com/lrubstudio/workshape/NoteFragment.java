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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
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
        final View view = inflater.inflate(R.layout.fragment_note, container, false);
        // fill with db field
        MainActivity.getLastRequestedProduct().fillFragmentEditsFromFields(view, new int [] { R.id.editNote }, new String [] { DbProduct.note } );

        // set save button invisible
        view.findViewById(R.id.buttonActionNote).setVisibility(View.GONE);

        // setup TextChangedListener handlers on edit
        final String originalString = ((EditText)view.findViewById(R.id.editNote)).getText().toString();

        ((EditText)view.findViewById(R.id.editNote)).addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    public void afterTextChanged(Editable s)
                    {
                        String entry = ((EditText)view.findViewById(R.id.editNote)).getText().toString();
                        if (entry.equals(originalString))
                        {
                            ((EditAddActivity)getActivity()).setNoteFragmentModified(false);
                            view.findViewById(R.id.buttonActionNote).setVisibility(View.GONE);
                        }
                        else
                        {
                            ((EditAddActivity)getActivity()).setNoteFragmentModified(true);
                            view.findViewById(R.id.buttonActionNote).setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        // manage button
        view.findViewById(R.id.buttonActionNote).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        // build and run request
        String qrCode = MainActivity.getLastRequestedProduct().getQrCode();
        if (qrCode.length() > 0)
        {
            String note = ((EditText)getView().findViewById(R.id.editNote)).getText().toString();
            DbRequest.createRequest(this, null).execute(DbProduct.buildRequestProductUpdateNote(getActivity(), qrCode, DbProduct.timeNowToString(getActivity()), note));
        }
        else
        {
            // unknown qr code !
            Toast.makeText(getActivity(), getActivity().getString(R.string.qrcode_inconnu), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
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
