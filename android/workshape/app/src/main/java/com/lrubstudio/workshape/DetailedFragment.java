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
    private boolean isNewPiece = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detailed, container, false);

        if (view != null)
        {
            boolean newPiece;

            // see if piece is added
            Bundle bundle = this.getArguments();
            if(bundle != null)
            {
                // yes, fill only QRCode
                newPiece = bundle.getBoolean("newPiece");
                if (newPiece && MainActivity.getLastRequestedPiece().getTemporaryQrCode().length()>0)
                {
                    ((EditText)view.findViewById(R.id.editQRCode)).setText(MainActivity.getLastRequestedPiece().getTemporaryQrCode());

                    // keep it's a new piece
                    isNewPiece = true;
                }
                else
                {
                    // bloody mystery
                    Toast.makeText(view.getContext(), getResources().getString(R.string.internal_problem), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                // fill MMI views from db fields
                int[] edits = new int [] {
                        R.id.editQRCode, R.id.editReference, R.id.editFournisseur, R.id.editRefFournisseur,
                        R.id.editDateArrivee, R.id.editTransportFrigo, R.id.editLongueurInitiale,
                        R.id.editLargeur, R.id.editGrammage, R.id.editTypeDeTissus
                };
                String[] dbfields = new String [] {
                        DbPiece.qrCode, DbPiece.reference, DbPiece.fournisseur, DbPiece.refFournisseur,
                        DbPiece.dateArrivee, DbPiece.transportFrigo, DbPiece.longueurInitiale,
                        DbPiece.largeur, DbPiece.grammage, DbPiece.typeDeTissus
                };
                MainActivity.getLastRequestedPiece().fillFragmentEditsFromFields(view, edits, dbfields);

                // set save button invisible
                view.findViewById(R.id.buttonAddModify).setVisibility(View.GONE);

                // setup TextChangedListener handlers on each edit
                for (int i = 0; i < edits.length; i++)
                {
                    final String originalString = MainActivity.getLastRequestedPiece().get(dbfields[i]);
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
                                        view.findViewById(R.id.buttonAddModify).setVisibility(View.GONE);
                                    else
                                        view.findViewById(R.id.buttonAddModify).setVisibility(View.VISIBLE);
                                }
                            }
                    );
                }
            }

            // manage button
            view.findViewById(R.id.buttonAddModify).setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view)
    {
        String qrCode = MainActivity.getLastRequestedPiece().getTemporaryQrCode();
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

        // new piece: create it
        if (isNewPiece)
        {
            // build and run request
            new DbRequest(this).execute(DbPiece.buildRequestProductAdd(getActivity(),
                  qrCode, date, reference, fournisseur, refFournisseur,
                  longueurInitiale, largeur, grammage,
                  typeDeTissus, dateArrivee, transportFrigo, lieuActuel));
        }
        // piece to update: update it
        else
        {
            // build and run request
            new DbRequest(this).execute(DbPiece.buildRequestProductUpdate(getActivity(),
                    qrCode, date, reference, fournisseur, refFournisseur,
                    longueurInitiale, largeur, grammage,
                    typeDeTissus, dateArrivee, transportFrigo, lieuActuel));
        }
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        if (dbError == DbRequest.DBERR_OK)
        {
            // toast ok !
            if (isNewPiece)
                Toast.makeText(getActivity(), getActivity().getString(R.string.piece_creee), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), getActivity().getString(R.string.piece_sauvee), Toast.LENGTH_LONG).show();

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
