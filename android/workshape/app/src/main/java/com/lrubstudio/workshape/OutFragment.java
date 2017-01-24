package com.lrubstudio.workshape;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // fragment should scroll when editing a view
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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
            view.findViewById(R.id.buttonOutLieuDepuis).setEnabled(false);

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
                R.id.editOutLongueurActuelle, R.id.editOutLieuActuel, R.id.buttonOutLieuDepuis };
        String[] dbfields = new String [] {
                DbProduct.qrCode, DbProduct.name, DbProduct.longueurInitiale,
                DbProduct.longueurActuelle, DbProduct.lieuActuel, DbProduct.lieuDepuis };
        MainActivity.getLastRequestedProduct().fillFragmentFromFields(view, edits, dbfields);

        // total time in seconds -> "hh:mm"
        ((EditText)view.findViewById(R.id.editOutHorsGelTotal)).setText(DbProduct.secondsToHHMM(getActivity(), MainActivity.getLastRequestedProduct().get(DbProduct.tempsHorsGelTotal)));

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        // build then run request
        String qrCode=((EditText)getView().findViewById(R.id.editOutQRCode)).getText().toString();
        String editOutLongueurActuelle = ((EditText)getView().findViewById(R.id.editOutLongueurActuelle)).getText().toString();
        String editOutHorsGelTotal = ((EditText)getView().findViewById(R.id.editOutHorsGelTotal)).getText().toString();
        String buttonOutLieuDepuis = ((Button)getView().findViewById(R.id.buttonOutLieuDepuis)).getText().toString();

        DbRequest.createCommand(this, null).execute(DbProduct.buildRequestProductOut(getActivity(), qrCode, DbProduct.timeNowToString(getActivity()),
                buttonOutLieuDepuis, editOutLongueurActuelle, editOutHorsGelTotal));
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString)
    {
        if (dbError == DbRequest.Error.ok)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.produit_sorti), Toast.LENGTH_LONG).show();

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
