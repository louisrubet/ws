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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        // fill hors gel time duration
        try
        {
            // time diff
            Date currentDate = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat(view.getResources().getString(R.string.date_format_to_android));
            String dateFrom = ((Button)view.findViewById(R.id.buttonInLieuDepuis)).getText().toString();
            Date dateLieu = dateFormat.parse(dateFrom);

            // building hours
            long diffS = (currentDate.getTime() - dateLieu.getTime()) / 1000;
            long hours = diffS / 3600;
            String hoursString = String.format("%02d", hours);

            // building minutes
            long minutes = (diffS - 3600 * (diffS / 3600)) / 60;
            String minutesString = String.format("%02d", minutes);

            String diffString = view.getResources().getString(R.string.time_format_to_android);
            diffString = diffString.replaceAll("HH", hoursString);
            diffString = diffString.replaceAll("mm", minutesString);

            // setting text
            ((EditText)view.findViewById(R.id.editInTempsHorsGel)).setText(diffString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        // check entries
        if (checkShouldBeDecimal(R.id.editInLongueurConsommee) && checkShouldBeHHmm(R.id.editInTempsHorsGel))
        {
            // build and run request
            String qrCode=((EditText)getView().findViewById(R.id.editInQRCode)).getText().toString();
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            String longueurConsommee = ((EditText)getView().findViewById(R.id.editInLongueurConsommee)).getText().toString();
            String temps_hors_gel = ((EditText)getView().findViewById(R.id.editInTempsHorsGel)).getText().toString();

            new DbRequest(this).execute(DbProduct.buildRequestProductIn(getActivity(), qrCode, date, longueurConsommee, temps_hors_gel));
        }
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
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

    private boolean checkShouldBeDecimal(int resource)
    {
        boolean ret = false;
        EditText edit = (EditText)getView().findViewById(resource);
        if (edit != null)
        {
            String entry_string = edit.getText().toString();

            try
            {
                float entry_float = Float.parseFloat(entry_string);
                edit.setError(null);
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                ret =true;
            }
            catch(Exception e)
            {
                edit.setError(getResources().getString(R.string.should_be_decimal));
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_24dp, 0);
            }
        }

        return ret;
    }

    private boolean checkShouldBeHHmm(int resource)
    {
        boolean ret = false;
        EditText edit = (EditText)getView().findViewById(resource);
        if (edit != null)
        {
            String entry_string = edit.getText().toString();

            try
            {
                DateFormat df = new SimpleDateFormat("HH:mm");
                Date result =  df.parse(edit.getText().toString());
                edit.setError(null);
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                ret =true;
            }
            catch(Exception e)
            {
                edit.setError(getResources().getString(R.string.should_be_hhmm));
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_24dp, 0);
            }
        }

        return ret;
    }
}
