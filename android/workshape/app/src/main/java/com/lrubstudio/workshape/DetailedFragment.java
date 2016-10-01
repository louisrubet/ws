package com.lrubstudio.workshape;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends Fragment
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
        View view = inflater.inflate(R.layout.fragment_detailed, container, false);

        if (view != null)
        {
            // fill MMI views from db fields
            int[] edits = new int [] {
                    R.id.editQRCode, R.id.editReference, R.id.editFournisseur, R.id.editRefFournisseur,
                    R.id.editDateArrivee, R.id.editTransportFrigo, R.id.editLongueurInitiale,
                    R.id.editLargeur, R.id.editGrammage, R.id.editTypeDeTissus
            };
            String[] dbfields = new String [] {
                    "qr_code", "reference", "fournisseur", "ref_fournisseur",
                    "date_arrivee", "transport_frigo", "longueur_initiale",
                    "largeur", "grammage", "type_de_tissus"
            };
            MainActivity.getLastRequestedPiece().fillFragmentEditsFromFields(view, edits, dbfields);
        }

        return view;
    }
}
