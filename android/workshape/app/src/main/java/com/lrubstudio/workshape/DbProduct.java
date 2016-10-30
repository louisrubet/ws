package com.lrubstudio.workshape;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static final String name = "name";
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
    private ArrayList<Map> mapArray;
    public void setFromMap(ArrayList<Map> mapArray)
    {
        this.mapArray = mapArray;
    }

    // build product requests
    //
    static public String buildRequestProductView(Context context, String qrCode)
    {
        String request = context.getString(R.string.request_product);
        return request.replaceAll("#qr_code", qrCode);
    }

    static public String buildRequestProductList(Context context)
    {
        return context.getString(R.string.request_product_list);
    }

    static public String buildRequestProductIn(Context context, String qrCode, String date, String longueurConsommee, String temps_hors_gel, String lieuActuel)
    {
        String request = context.getString(R.string.request_product_in);
        request = request.replaceAll("#qr_code", qrCode);
        request = request.replaceAll("#date", date);
        request = request.replaceAll("#longueur_consommee", longueurConsommee);
        request = request.replaceAll("#temps_hors_gel", temps_hors_gel);
        request = request.replaceAll("#lieu_actuel", lieuActuel);
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

    static public String secondsToHHMM(Context context, String secondsString)
    {
        int seconds = Integer.parseInt(secondsString);
        String hhmm = context.getString(R.string.time_format_to_android);
        String hoursString = String.format("%02d", seconds / 3600);
        String minutesString = String.format("%02d", (seconds % 3600) / 60);

        hhmm = hhmm.replaceAll("HH", hoursString);
        hhmm = hhmm.replaceAll("mm", minutesString);

        return hhmm;
    }

    static public String HHMMToSeconds(Context context, String hhmmString)
    {
        String[] parts = hhmmString.split(":");
        String seconds = null;
        int secondsInt = -1;

        try
        {
            secondsInt = (Integer.parseInt(parts[0]) * 3600) + (Integer.parseInt(parts[1]) * 60);
            seconds = String.valueOf(secondsInt);
        }
        catch(Exception e)
        {
        }

        return seconds;
    }

    static public String timeDiffToString(Context context, String dateFrom, Date dateTo)
    {
        String diffString = null;
        try
        {
            // time diff
            SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.date_format_to_android));
            Date dateLieu = dateFormat.parse(dateFrom);

            // building hours
            long diffS = (dateTo.getTime() - dateLieu.getTime()) / 1000;
            long hours = diffS / 3600;
            String hoursString = String.format("%02d", hours);

            // building minutes
            long minutes = (diffS - 3600 * (diffS / 3600)) / 60;
            String minutesString = String.format("%02d", minutes);

            diffString = context.getString(R.string.time_format_to_android);
            diffString = diffString.replaceAll("HH", hoursString);
            diffString = diffString.replaceAll("mm", minutesString);
        }
        catch(Exception e)
        {
        }
        return diffString;
    }

    static public String timeNowToString(Context context)
    {
        return new SimpleDateFormat(context.getString(R.string.date_format_to_mysql)).format(new Date());
    }

    static public ArrayList<Map> setDbgValuesNew()
    {
        return null;
    }

    static public ArrayList<Map> setDbgValuesOut()
    {
        Map tmp_map = new HashMap();
        tmp_map.put("qr_code", "abcd1234");
        tmp_map.put("idproduct", "123456");
        tmp_map.put("reference", "product ref");
        tmp_map.put("fournisseur", "Fournisseur");
        tmp_map.put("ref_fournisseur", "Ref Fournisseur");
        tmp_map.put("longueur_initiale", "25.00");
        tmp_map.put("longueur_actuelle", "7.50");
        tmp_map.put("largeur", "1.25");
        tmp_map.put("grammage", "1 kg / m2");
        tmp_map.put("type_de_tissus", "Sergé");
        tmp_map.put("date_arrivee", "01/01/2016");
        tmp_map.put("transport_frigo", "Oui");
        tmp_map.put("lieu_actuel", "frigo");
        tmp_map.put("lieu_depuis", "01/01/2016");
        tmp_map.put("temps_hors_gel_total", "02:30");
        tmp_map.put("note", "Note sur la pièce");

        ArrayList<Map> mapList = new ArrayList<Map>();
        mapList.add(tmp_map);
        return mapList;
    }

    static public ArrayList<Map> setDbgValuesIn()
    {
        Map tmp_map = new HashMap();
        tmp_map.put("qr_code", "abcd1234");
        tmp_map.put("idproduct", "123456");
        tmp_map.put("reference", "product ref");
        tmp_map.put("fournisseur", "Fournisseur");
        tmp_map.put("ref_fournisseur", "Ref Fournisseur");
        tmp_map.put("longueur_initiale", "25.00");
        tmp_map.put("longueur_actuelle", "7.50");
        tmp_map.put("largeur", "1.25");
        tmp_map.put("grammage", "1 kg / m2");
        tmp_map.put("type_de_tissus", "Sergé");
        tmp_map.put("date_arrivee", "01/01/2016");
        tmp_map.put("transport_frigo", "Oui");
        tmp_map.put("lieu_actuel", "");
        tmp_map.put("lieu_depuis", "17/10/2016 15:28");
        tmp_map.put("temps_hors_gel_total", "02:30");
        tmp_map.put("note", "Note sur la pièce");

        ArrayList<Map> mapList = new ArrayList<Map>();
        mapList.add(tmp_map);
        return mapList;
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
                // fill an EditText
                String value = this.get(dbfields[text_id]);
                ((EditText)fragment.findViewById(id)).setText(value);
            }
            catch(Exception eEdit)
            {
                // fill a Button
                try
                {
                    String value = this.get(dbfields[text_id]);
                    ((Button) fragment.findViewById(id)).setText(value);
                }
                catch(Exception eButton)
                {
                    // nothing
                }
            }
            text_id += 1;
        }
    }

    public int getRows()
    {
        int rows = 0;
        if (mapArray != null && mapArray.get(0) != null)
            rows = mapArray.size();
        return rows;
    }

    public String get(String fieldName)
    {
        try
        {
            if (mapArray.get(0).containsKey(fieldName))
                return (String)mapArray.get(0).get(fieldName);
            else
                return "";
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public String get(int row, String fieldName)
    {
        try
        {
            if (mapArray.get(row).containsKey(fieldName))
                return (String)mapArray.get(row).get(fieldName);
            else
                return "";
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
