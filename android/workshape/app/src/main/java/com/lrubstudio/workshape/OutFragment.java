package com.lrubstudio.workshape;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
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
        return inflater.inflate(R.layout.fragment_out, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        View view = getView();

        // manage MMI: disable some edits
        try
        {
            view.findViewById(R.id.editOutQRCode).setEnabled(false);
            view.findViewById(R.id.editOutReference).setEnabled(false);
            view.findViewById(R.id.editOutLongueurInitiale).setEnabled(false);
            view.findViewById(R.id.editOutLongueurActuelle).setEnabled(false);
            view.findViewById(R.id.editOutHorsGelTotal).setEnabled(false);
            view.findViewById(R.id.editOutLieuActuel).setEnabled(false);
            view.findViewById(R.id.editOutLieuDepuis).setEnabled(false);

            // manage button
            view.findViewById(R.id.buttonActionOut).setOnClickListener(this);
        }
        catch(Exception e)
        {
            // bloody mystery
            Toast.makeText(getActivity(), getResources().getString(R.string.internal_problem), Toast.LENGTH_LONG).show();
            getActivity().finish();
        }

        // fill MMI views from db fields
        int[] edits = new int [] {
                R.id.editOutQRCode, R.id.editOutReference, R.id.editOutLongueurInitiale,
                R.id.editOutLongueurActuelle, R.id.editOutHorsGelTotal, R.id.editOutLieuActuel,
                R.id.editOutLieuDepuis };
        String[] dbfields = new String [] {
                DbPiece.qrCode, DbPiece.reference, DbPiece.longueurInitiale,
                DbPiece.longueurActuelle, DbPiece.tempsHorsGelTotal, DbPiece.lieuActuel,
                DbPiece.lieuDepuis
        };
        MainActivity.getLastRequestedPiece().fillFragmentEditsFromFields(view, edits, dbfields);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        // build then run request
        String qrCode=((EditText)getView().findViewById(R.id.editOutQRCode)).getText().toString();
        String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
        new DbRequest(this).execute(DbPiece.buildRequestProductOut(getActivity(), qrCode, date));
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.piece_sortie), Toast.LENGTH_LONG).show();

            // bye
            getActivity().finish();
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
