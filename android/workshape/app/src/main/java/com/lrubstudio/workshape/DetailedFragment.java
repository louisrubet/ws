package com.lrubstudio.workshape;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends DataFragment implements View.OnClickListener, DbRequest.AsyncResponse, DateTimeGetter.onDateTimeGetter
{
    // widget ids to be filled with db fields
    int[] _ids = new int [] {
            R.id.editName, R.id.editFournisseur, R.id.editRefFournisseur,
            R.id.editTransportFrigo, R.id.editLongueurInitiale, R.id.buttonDateArrivee,
            R.id.editLargeur, R.id.editGrammage, R.id.editTypeDeTissus, R.id.editLieuActuel, R.id.switchFinished,
            R.id.editDureeDeVie20, R.id.editDureeDeVieMoins18
    };

    // db fields to populate widgets
    String[] _fields = new String [] {
            DbProduct.name, DbProduct.fournisseur, DbProduct.refFournisseur,
            DbProduct.transportFrigo, DbProduct.longueurInitiale, DbProduct.dateArrivee,
            DbProduct.largeur, DbProduct.grammage, DbProduct.typeDeTissus, DbProduct.lieuActuel, DbProduct.finished,
            DbProduct.dureeDeVie20, DbProduct.dureeDeVieMoins18
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detailed, container, false);

        // manage button
        view.findViewById(R.id.buttonAddModify).setOnClickListener(this);

        // manage dates
        view.findViewById(R.id.buttonDateArrivee).setOnClickListener(this);

        // see if product is added
        if(! MainActivity.getLastRequestedProduct().isNewQrCode())
        {
            // no, fill MMI views from db fields
            MainActivity.getLastRequestedProduct().fillFragmentFromFields(view, _ids, _fields);

            // record Edits to watch for modification
            ViewWatcher.AddMultipleWidgetsToWatcher(this, view, _ids);

            // set save button state
            if (((EditAddActivity)getActivity()).isDetailedFragmentModified())
                view.findViewById(R.id.buttonAddModify).setVisibility(View.VISIBLE);
            else
                view.findViewById(R.id.buttonAddModify).setVisibility(View.GONE);

            // dimm all for now
            enableMMI(view, false);
        }
        else
        {
            // yes (new product)
            // mark current product as modified in order to prevent leaving without question
            ((EditAddActivity)getActivity()).setDetailedFragmentModified(true);

            // set defaults
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            ((Button)view.findViewById(R.id.buttonDateArrivee)).setText(date);
            ((EditText)view.findViewById(R.id.editLieuActuel)).setText(ConfigurationActivity.configuration.lieuParDefaut);

            // all widgets enabled
            enableMMI(view, true);
        }

        // manage floating edit button
        view.findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                enableMMI(getView(), true);
                ((FloatingActionButton)view).hide();
            }
        });

        return view;
    }

    void enableMMI(View view, boolean enable)
    {
        if (view != null)
            for (int id : _ids)
                view.findViewById(id).setEnabled(enable);
    }

    @Override
    public void onClick(View view)
    {
        // button 'save'
        if (view.getId() == R.id.buttonAddModify)
        {
            String qrCode = MainActivity.getLastRequestedProduct().getQrCode();
            String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
            String name = ((EditText) getView().findViewById(R.id.editName)).getText().toString();
            String fournisseur = ((EditText) getView().findViewById(R.id.editFournisseur)).getText().toString();
            String refFournisseur = ((EditText) getView().findViewById(R.id.editRefFournisseur)).getText().toString();
            String longueurInitiale = ((EditText) getView().findViewById(R.id.editLongueurInitiale)).getText().toString();
            String largeur = ((EditText) getView().findViewById(R.id.editLargeur)).getText().toString();
            String grammage = ((EditText) getView().findViewById(R.id.editGrammage)).getText().toString();
            String typeDeTissus = ((EditText) getView().findViewById(R.id.editTypeDeTissus)).getText().toString();
            String dateArrivee = ((Button) getView().findViewById(R.id.buttonDateArrivee)).getText().toString();
            String transportFrigo = ((EditText) getView().findViewById(R.id.editTransportFrigo)).getText().toString();
            String lieuActuel = ((EditText) getView().findViewById(R.id.editLieuActuel)).getText().toString();
            String dureeDeVie20 = ((EditText) getView().findViewById(R.id.editDureeDeVie20)).getText().toString();
            String dureeDeVieMoins18 = ((EditText) getView().findViewById(R.id.editDureeDeVieMoins18)).getText().toString();
            String finished = ((Switch) getView().findViewById(R.id.switchFinished)).isChecked() ? "1":"0";

            // new product: create it
            if (MainActivity.getLastRequestedProduct().isNewQrCode())
                // build and run request
                DbRequest.createCommand(this, null).execute(DbProduct.buildRequestProductAdd(getActivity(),
                        qrCode, date, name, fournisseur, refFournisseur,
                        longueurInitiale, largeur, grammage,
                        typeDeTissus, dateArrivee, transportFrigo, lieuActuel, finished, dureeDeVie20, dureeDeVieMoins18,
                        getResources().getString(R.string.event_label_add), getResources().getString(R.string.name_label_add)));
                // product to update: update it
            else
                // build and run request
                DbRequest.createCommand(this, null).execute(DbProduct.buildRequestProductUpdate(getActivity(),
                        qrCode, date, name, fournisseur, refFournisseur,
                        longueurInitiale, largeur, grammage,
                        typeDeTissus, dateArrivee, transportFrigo, lieuActuel, finished, dureeDeVie20, dureeDeVieMoins18,
                        getResources().getString(R.string.event_label_update)));
        }
        else if(view.getId() == R.id.buttonDateArrivee)
        {
            String dateArrivee = ((Button)getView().findViewById(R.id.buttonDateArrivee)).getText().toString();

            // choose date then time
            (new DateTimeGetter(getActivity(), this, R.id.buttonDateArrivee, dateArrivee)).run();
        }
    }

    @Override
    public void wasModified(boolean modified)
    {
        if (((EditAddActivity) getActivity()).isDetailedFragmentModified() != modified)
        {
            // user modified an Edit (or cancelled a previous modification)
            // show or hide modified button
            getView().findViewById(R.id.buttonAddModify).setVisibility(modified ? View.VISIBLE : View.GONE);

            // mark this fragment as modified
            ((EditAddActivity) getActivity()).setDetailedFragmentModified(modified);
        }
    }

    public void onDateTimeGetter(int id, String dateTime)
    {
        if (id == R.id.buttonDateArrivee)
            ((Button)getView().findViewById(R.id.buttonDateArrivee)).setText(dateTime);
    }

    @Override
    public void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString)
    {
        if (dbError == DbRequest.Error.ok)
        {
            // toast ok !
            if (MainActivity.getLastRequestedProduct().isNewQrCode())
                Toast.makeText(getActivity(), getActivity().getString(R.string.produit_cree), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), getActivity().getString(R.string.produit_sauve), Toast.LENGTH_LONG).show();

            // don't see button
            getView().findViewById(R.id.buttonAddModify).setVisibility(View.GONE);

            // not modified anymore
            ((EditAddActivity)getActivity()).setDetailedFragmentModified(false);

            // hide shown keyboard
            hideKeyboard();

            // after creation: back to home
            if (MainActivity.getLastRequestedProduct().isNewQrCode())
                getActivity().finish();
            else
            {
                // else correct db data
                MainActivity.getLastRequestedProduct().fillFieldsFromFragment(getView(), _ids, _fields);

                // record Edits to watch for modification
                ViewWatcher.AddMultipleWidgetsToWatcher(this, getView(), _ids);

                // disable MMI again
                enableMMI(getView(), false);
                ((FloatingActionButton)getView().findViewById(R.id.buttonEdit)).show();
            }
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
