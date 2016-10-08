package com.lrubstudio.workshape;

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
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
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
        return inflater.inflate(R.layout.fragment_detailed, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        final View view = getView();

        // see if product is added
        if(! MainActivity.getLastRequestedProduct().isNewQrCode())
        {
            // fill MMI views from db fields
            int[] edits = new int [] {
                    R.id.editReference, R.id.editFournisseur, R.id.editRefFournisseur,
                    R.id.editDateArrivee, R.id.editTransportFrigo, R.id.editLongueurInitiale,
                    R.id.editLargeur, R.id.editGrammage, R.id.editTypeDeTissus
            };
            String[] dbfields = new String [] {
                    DbProduct.reference, DbProduct.fournisseur, DbProduct.refFournisseur,
                    DbProduct.dateArrivee, DbProduct.transportFrigo, DbProduct.longueurInitiale,
                    DbProduct.largeur, DbProduct.grammage, DbProduct.typeDeTissus
            };
            MainActivity.getLastRequestedProduct().fillFragmentEditsFromFields(view, edits, dbfields);

            // set save button invisible
            view.findViewById(R.id.buttonAddModify).setVisibility(View.GONE);

            // setup TextChangedListener handlers on each edit
            for (int i = 0; i < edits.length; i++)
            {
                final String originalString = MainActivity.getLastRequestedProduct().get(dbfields[i]);
                final int id = edits[i];

                ((EditText)view.findViewById(id)).addTextChangedListener(
                        new TextWatcher()
                        {
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                            public void onTextChanged(CharSequence s, int start, int before, int count) { }
                            public void afterTextChanged(Editable s)
                            {
                                String entry = ((EditText)view.findViewById(id)).getText().toString();
                                if (entry.equals(originalString))
                                {
                                    ((EditAddActivity)getActivity()).setModified(false);
                                    view.findViewById(R.id.buttonAddModify).setVisibility(View.GONE);
                                }
                                else
                                {
                                    ((EditAddActivity)getActivity()).setModified(true);
                                    view.findViewById(R.id.buttonAddModify).setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );
            }
        }

        // manage button
        view.findViewById(R.id.buttonAddModify).setOnClickListener(this);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        String qrCode = MainActivity.getLastRequestedProduct().getQrCode();
        String date = new SimpleDateFormat(getActivity().getString(R.string.date_format_to_mysql)).format(new Date());
        String reference = ((EditText)getView().findViewById(R.id.editReference)).getText().toString();
        String fournisseur = ((EditText)getView().findViewById(R.id.editFournisseur)).getText().toString();
        String refFournisseur = ((EditText)getView().findViewById(R.id.editRefFournisseur)).getText().toString();
        String longueurInitiale = ((EditText)getView().findViewById(R.id.editLongueurInitiale)).getText().toString();
        String largeur = ((EditText)getView().findViewById(R.id.editLargeur)).getText().toString();
        String grammage = ((EditText)getView().findViewById(R.id.editGrammage)).getText().toString();
        String typeDeTissus = ((EditText)getView().findViewById(R.id.editTypeDeTissus)).getText().toString();
        String dateArrivee = ((EditText)getView().findViewById(R.id.editDateArrivee)).getText().toString();
        String transportFrigo = ((EditText)getView().findViewById(R.id.editTransportFrigo)).getText().toString();
        String lieuActuel = ConfigurationActivity.configuration.lieuParDefaut;

        // new product: create it
        if (MainActivity.getLastRequestedProduct().isNewQrCode())
            // build and run request
            new DbRequest(this).execute(DbProduct.buildRequestProductAdd(getActivity(),
                  qrCode, date, reference, fournisseur, refFournisseur,
                  longueurInitiale, largeur, grammage,
                  typeDeTissus, dateArrivee, transportFrigo, lieuActuel));
        // product to update: update it
        else
            // build and run request
            new DbRequest(this).execute(DbProduct.buildRequestProductUpdate(getActivity(),
                    qrCode, date, reference, fournisseur, refFournisseur,
                    longueurInitiale, largeur, grammage,
                    typeDeTissus, dateArrivee, transportFrigo, lieuActuel));
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            if (MainActivity.getLastRequestedProduct().isNewQrCode())
                Toast.makeText(getActivity(), getActivity().getString(R.string.produit_cree), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), getActivity().getString(R.string.produit_sauve), Toast.LENGTH_LONG).show();

            // don't see button
            getView().findViewById(R.id.buttonAddModify).setVisibility(View.GONE);
        }
        else
        {
            // toast db error
            Toast.makeText(getActivity(), dbErrorString, Toast.LENGTH_LONG).show();
        }
    }
}
