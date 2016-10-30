package com.lrubstudio.workshape;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class InFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
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
        return inflater.inflate(R.layout.fragment_in, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        View view = getView();

        try
        {
            // manage MMI: disable some edits
            view.findViewById(R.id.editInQRCode).setEnabled(false);
            view.findViewById(R.id.editInReference).setEnabled(false);
            view.findViewById(R.id.editInLongueurInitiale).setEnabled(false);
            view.findViewById(R.id.editInLongueurActuelle).setEnabled(false);
            view.findViewById(R.id.editInHorsGelTotal).setEnabled(false);
            view.findViewById(R.id.buttonInLieuDepuis).setEnabled(false);

            // manage button
            view.findViewById(R.id.buttonActionIn).setOnClickListener(this);
        }
        catch(Exception e)
        {
            // bloody mystery
            Toast.makeText(getActivity(), getResources().getString(R.string.internal_problem), Toast.LENGTH_LONG).show();
            getActivity().finish();
        }

        // fill MMI views from db fields
        int[] edits = new int [] {
                R.id.editInQRCode, R.id.editInReference, R.id.editInLongueurInitiale,
                R.id.editInLongueurActuelle, R.id.editInHorsGelTotal, R.id.buttonInLieuDepuis };
        String[] dbfields = new String [] {
                DbProduct.qrCode, DbProduct.name, DbProduct.longueurInitiale,
                DbProduct.longueurActuelle, DbProduct.tempsHorsGelTotal, DbProduct.lieuDepuis };
        MainActivity.getLastRequestedProduct().fillFragmentEditsFromFields(view, edits, dbfields);

        // fill lieu from global configuration
        ((EditText)view.findViewById(R.id.editInLieuActuel)).setText(ConfigurationActivity.configuration.lieuParDefaut);

        // fill hors gel time duration
        ((EditText)view.findViewById(R.id.editInTempsHorsGel)).setText(DbProduct.timeDiffToString(getActivity(), ((Button)view.findViewById(R.id.buttonInLieuDepuis)).getText().toString(), new Date(System.currentTimeMillis())));

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        // check entries
        if (StringValidation.EditTextShouldBeDecimal((EditText)getView().findViewById(R.id.editInLongueurConsommee))
                && StringValidation.EditTextShouldBeHHmm((EditText)getView().findViewById(R.id.editInTempsHorsGel)))
        {
            // build and run request
            String qrCode=((EditText)getView().findViewById(R.id.editInQRCode)).getText().toString();
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            String longueurConsommee = ((EditText)getView().findViewById(R.id.editInLongueurConsommee)).getText().toString();
            String temps_hors_gel = ((EditText)getView().findViewById(R.id.editInTempsHorsGel)).getText().toString();
            String lieuActuel= ((EditText)getView().findViewById(R.id.editInLieuActuel)).getText().toString();

            new DbRequest(this).execute(DbProduct.buildRequestProductIn(getActivity(), qrCode, date, longueurConsommee, temps_hors_gel, lieuActuel));
        }
    }

    @Override
    public void dbRequestFinished(ArrayList<Map> result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.produit_entre), Toast.LENGTH_LONG).show();

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
