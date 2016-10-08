package com.lrubstudio.workshape;

/**
 * Created by louis on 05/10/16.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        final View view = getView();

        // fill with db field
        MainActivity.getLastRequestedPiece().fillFragmentEditsFromFields(view, new int [] { R.id.editNote }, new String [] { DbPiece.note } );

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
                            ((EditAddActivity)getActivity()).setModified(false);
                            view.findViewById(R.id.buttonActionNote).setVisibility(View.GONE);
                        }
                        else
                        {
                            ((EditAddActivity)getActivity()).setModified(true);
                            view.findViewById(R.id.buttonActionNote).setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        // manage button
        view.findViewById(R.id.buttonActionNote).setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        // build and run request
        String qrCode = MainActivity.getLastRequestedPiece().getQrCode();
        if (qrCode.length() > 0)
        {
            String note = ((EditText)getView().findViewById(R.id.editNote)).getText().toString();
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            new DbRequest(this).execute(DbPiece.buildRequestProductUpdateNote(getActivity(), qrCode, date, note));
        }
        else
        {
            // unknown qr code !
            Toast.makeText(getActivity(), getActivity().getString(R.string.qrcode_inconnu), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.note_sauvee), Toast.LENGTH_LONG).show();

            // don't see button
            getView().findViewById(R.id.buttonActionNote).setVisibility(View.GONE);
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
