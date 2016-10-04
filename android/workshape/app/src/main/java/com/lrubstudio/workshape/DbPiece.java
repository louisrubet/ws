package com.lrubstudio.workshape;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by louis on 03/09/16.
 */
public class DbPiece
{
    // db field names
    public final String idproduct = "idproduct";
    public final String reference = "reference";
    public final String qr_code = "qr_code";
    public final String fournisseur = "fournisseur";
    public final String ref_fournisseur = "ref_fournisseur";
    public final String longueur_initiale = "longueur_initiale";
    public final String longueur_actuelle = "longueur_actuelle";
    public final String largeur = "largeur";
    public final String grammage = "grammage";
    public final String type_de_tissus = "type_de_tissus";
    public final String date_arrivee = "date_arrivee";
    public final String transport_frigo = "transport_frigo";
    public final String lieu_actuel = "lieu_actuel";
    public final String temps_hors_gel_total = "temps_hors_gel_total";
    public final String nb_decongelation = "nb_decongelation";

    // db values (input field name, output value)
    private Map map;
    public void setFromMap(Map map)
    {
        this.map = map;
    }

    // build product requests
    //
    static public String buildRequestProductView(Context context, String qrCode)
    {
        String request = context.getString(R.string.request_product);
        return request.replaceAll("#qr_code", qrCode);
    }

    static public String buildRequestProductIn(Context context, String qrCode, String date, String longueurConsommee, String temps_hors_gel)
    {
        String request = context.getString(R.string.request_product_in);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        request = request.replaceAll("#longueur_consommee", longueurConsommee);
        request = request.replaceAll("#temps_hors_gel", temps_hors_gel);
        return request;
    }

    static public String buildRequestProductOut(Context context, String qrCode, String date)
    {
        String request = context.getString(R.string.request_product_out);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        return request;
    }

    static public Map setDbgValues()
    {
        Map tmp_map = null;

        try
        {
            tmp_map = new HashMap();
            tmp_map.put("idproduct", "123456");
            tmp_map.put("reference", "product ref");
            tmp_map.put("qr_code", "abcd1234");
            tmp_map.put("fournisseur", "Fournisseur");
            tmp_map.put("ref_fournisseur", "Ref Fournisseur");
            tmp_map.put("longueur_initiale", "25.00");
            tmp_map.put("longueur_actuelle", "7.50");
            tmp_map.put("largeur", "1.25");
            tmp_map.put("grammage", "1 kg / m2");
            tmp_map.put("type_de_tissus", "Sergé");
            tmp_map.put("date_arrivee", "1 janvier 2016");
            tmp_map.put("transport_frigo", "Oui");
            tmp_map.put("decongelele", "2 juin 2016 11h00");
            tmp_map.put("decongele", "4 fois");
            tmp_map.put("lieu_actuel", "frigo");
            tmp_map.put("temps_hors_gel_total", "02:30");
            tmp_map.put("nb_decongelation", "3");
        }
        finally
        {
        }

        return tmp_map;
    }

    public boolean isPieceOut()
    {
        // piece is considered out if 'lieu_actuel' is null or empty
        return get("lieu_actuel").isEmpty();
    }

    public void fillFragmentEditsFromFields(View fragment, int[] edits, String[] dbfields)
    {
        int text_id = 0;
        for (int id : edits)
        {
            try
            {
                String value = this.get(dbfields[text_id]);
                ((EditText) fragment.findViewById(id)).setText(value);
            }
            catch(Exception e)
            {
                //Toast.makeText(this, "Error with field \"" + dbfields[text_id] + "\"", Toast.LENGTH_LONG).show();
            }
            text_id += 1;
        }
    }

    public String get(String fieldName)
    {
        try
        {
            if (map.containsKey(fieldName))
                return (String) map.get(fieldName);
            else
                return "";
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
