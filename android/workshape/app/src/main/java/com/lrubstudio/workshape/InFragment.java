package com.lrubstudio.workshape;

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
public class InFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
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
        View view = inflater.inflate(R.layout.fragment_in, container, false);
        if (view != null)
        {
            try
            {
                // manage MMI: disable some edits
                view.findViewById(R.id.editInQRCode).setEnabled(false);
                view.findViewById(R.id.editInReference).setEnabled(false);
                view.findViewById(R.id.editInLongueurInitiale).setEnabled(false);
                view.findViewById(R.id.editInLongueurActuelle).setEnabled(false);
                view.findViewById(R.id.editInHorsGelTotal).setEnabled(false);
                view.findViewById(R.id.editInLieuDepuis).setEnabled(false);

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
                    R.id.editInLongueurActuelle, R.id.editInHorsGelTotal, R.id.editInLieuDepuis };
            String[] dbfields = new String [] {
                    "qr_code", "reference", "longueur_initiale",
                    "longueur_actuelle", "temps_hors_gel_total", "lieu_depuis" };
            MainActivity.getLastRequestedPiece().fillFragmentEditsFromFields(view, edits, dbfields);

            // fill hors gel time duration
            try
            {
                // time diff
                Date currentDate = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat(view.getResources().getString(R.string.date_format_to_android));
                String dateFrom = ((EditText)view.findViewById(R.id.editInLieuDepuis)).getText().toString();
                Date dateLieu = dateFormat.parse(dateFrom);

                long diffS = (currentDate.getTime() - dateLieu.getTime()) / 1000;
                String diffString = view.getResources().getString(R.string.time_format_to_android);

                // building hours
                long hours = diffS / 3600;
                String hoursString = new String();
                if (hours < 10)
                    hoursString += "0";
                hoursString += String.valueOf(hours);
                diffString = diffString.replaceAll("HH", hoursString);

                // building minutes
                long minutes = (diffS - 3600 * (diffS / 3600)) / 60;
                String minutesString = new String();
                if (minutes < 10)
                    minutesString += "0";
                minutesString += String.valueOf(minutes);
                diffString = diffString.replaceAll("mm", minutesString);

                // setting text
                ((EditText)view.findViewById(R.id.editInTempsHorsGel)).setText(diffString);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return view;
    }

    @Override
    public void onClick(View view)
    {
        // check entries
        if (checkShouldBeDecimal(R.id.editInLongueurConsommee) && checkShouldBeHHmm(R.id.editInTempsHorsGel))
        {
            // prepare request
            String request = getActivity().getString(R.string.request_product_in);

            String qr_code=((EditText)getView().findViewById(R.id.editInQRCode)).getText().toString();
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            String longueurConsommee = ((EditText)getView().findViewById(R.id.editInLongueurConsommee)).getText().toString();
            String temps_hors_gel = ((EditText)getView().findViewById(R.id.editInTempsHorsGel)).getText().toString();

            request = request.replaceAll("#qr_code", qr_code);
            request = request.replaceAll("#date", date);
            request = request.replaceAll("#longueur_consommee", longueurConsommee);
            request = request.replaceAll("#temps_hors_gel", temps_hors_gel);
            new DbRequest(this).execute(request);
        }
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            Toast.makeText(getActivity(), getActivity().getString(R.string.piece_entree), Toast.LENGTH_LONG).show();

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
