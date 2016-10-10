package com.lrubstudio.workshape;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by louis on 03/09/16.
 */
public class DbProduct
{
    // db field names
    public static final String idproduct = "idproduct";
    public static final String qrCode = "qr_code";
    public static final String reference = "reference";
    public static final String fournisseur = "fournisseur";
    public static final String refFournisseur = "ref_fournisseur";
    public static final String longueurInitiale = "longueur_initiale";
    public static final String longueurActuelle = "longueur_actuelle";
    public static final String largeur = "largeur";
    public static final String grammage = "grammage";
    public static final String typeDeTissus = "type_de_tissus";
    public static final String dateArrivee = "date_arrivee";
    public static final String transportFrigo = "transport_frigo";
    public static final String lieuActuel = "lieu_actuel";
    public static final String lieuDepuis = "lieu_depuis";
    public static final String tempsHorsGelTotal = "temps_hors_gel_total";
    public static final String note = "note";

    // product key column
    private String QrCode;
    public String getQrCode()
    {
        return QrCode;
    }

    public void setQrCode(String QrCode)
    {
        this.QrCode = QrCode;
    }

    //
    private boolean isNewQrCode = false;
    public boolean isNewQrCode()
    {
        return isNewQrCode;
    }

    public void setNewQrCode(boolean newQrCode)
    {
        isNewQrCode = newQrCode;
    }

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

    static public String buildRequestProductAdd(Context context, String qrCode, String date,
                                                String reference, String fournisseur, String refFournisseur,
                                                String longueurInitiale, String largeur, String grammage,
                                                String typeDeTissus, String dateArrivee, String transportFrigo,
                                                String lieuActuel)
    {
        String request = context.getString(R.string.request_product_add);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        request = request.replaceAll("#reference", reference);
        request = request.replaceAll("#fournisseur", fournisseur);
        request = request.replaceAll("#ref_fournisseur", refFournisseur);
        request = request.replaceAll("#longueur_initiale", longueurInitiale);
        request = request.replaceAll("#largeur", largeur);
        request = request.replaceAll("#grammage", grammage);
        request = request.replaceAll("#type_de_tissus", typeDeTissus);
        request = request.replaceAll("#datarrivee", dateArrivee);
        request = request.replaceAll("#transport_frigo", transportFrigo);
        request = request.replaceAll("#lieu_actuel", lieuActuel);
        return request;
    }

    static public String buildRequestProductUpdate(Context context, String qrCode, String date,
                                                String reference, String fournisseur, String refFournisseur,
                                                String longueurInitiale, String largeur, String grammage,
                                                String typeDeTissus, String dateArrivee, String transportFrigo,
                                                String lieuActuel)
    {
        String request = context.getString(R.string.request_product_update);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        request = request.replaceAll("#reference", reference);
        request = request.replaceAll("#fournisseur", fournisseur);
        request = request.replaceAll("#ref_fournisseur", refFournisseur);
        request = request.replaceAll("#longueur_initiale", longueurInitiale);
        request = request.replaceAll("#largeur", largeur);
        request = request.replaceAll("#grammage", grammage);
        request = request.replaceAll("#type_de_tissus", typeDeTissus);
        request = request.replaceAll("#datarrivee", dateArrivee);
        request = request.replaceAll("#transport_frigo", transportFrigo);
        request = request.replaceAll("#lieu_actuel", lieuActuel);
        return request;
    }

    static public String buildRequestProductUpdateNote(Context context, String qrCode, String date, String note)
    {
        String request = context.getString(R.string.request_product_update_note);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        request = request.replaceAll("#note", note);
        return request;
    }

    static public Map setDbgValuesNew()
    {
        return null;
    }

    static public Map setDbgValuesOut()
    {
        Map tmp_map = new HashMap();
        tmp_map.put("qrCode", "abcd1234");
        tmp_map.put("idproduct", "123456");
        tmp_map.put("reference", "product ref");
        tmp_map.put("fournisseur", "Fournisseur");
        tmp_map.put("refFournisseur", "Ref Fournisseur");
        tmp_map.put("longueurInitiale", "25.00");
        tmp_map.put("longueurActuelle", "7.50");
        tmp_map.put("largeur", "1.25");
        tmp_map.put("grammage", "1 kg / m2");
        tmp_map.put("typeDeTissus", "Sergé");
        tmp_map.put("dateArrivee", "1 janvier 2016");
        tmp_map.put("transportFrigo", "Oui");
        tmp_map.put("lieuActuel", "frigo");
        tmp_map.put("tempsHorsGelTotal", "02:30");
        tmp_map.put("note", "Note sur la pièce");
        return tmp_map;
    }

    static public Map setDbgValuesIn()
    {
        Map tmp_map = new HashMap();
        tmp_map.put("idproduct", "123456");
        tmp_map.put("reference", "product ref");
        tmp_map.put("qrCode", "abcd1234");
        tmp_map.put("fournisseur", "Fournisseur");
        tmp_map.put("refFournisseur", "Ref Fournisseur");
        tmp_map.put("longueurInitiale", "25.00");
        tmp_map.put("longueurActuelle", "7.50");
        tmp_map.put("largeur", "1.25");
        tmp_map.put("grammage", "1 kg / m2");
        tmp_map.put("typeDeTissus", "Sergé");
        tmp_map.put("dateArrivee", "1 janvier 2016");
        tmp_map.put("transportFrigo", "Oui");
        tmp_map.put("lieuActuel", "");
        tmp_map.put("tempsHorsGelTotal", "02:30");
        tmp_map.put("note", "Note sur la pièce");
        return tmp_map;
    }

    public boolean isProductOut()
    {
        // product is considered out if 'lieuActuel' is null or empty
        String value = get(DbProduct.lieuActuel);
        if (value != null && !value.isEmpty())
            return false;
        return true;
    }

    public void fillFragmentEditsFromFields(View fragment, int[] edits, String[] dbfields)
    {
        int text_id = 0;
        for (int id : edits)
        {
            try
            {
                String value = this.get(dbfields[text_id]);
                ((EditText)fragment.findViewById(id)).setText(value);
            }
            catch(Exception e)
            {
                // nothing
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
