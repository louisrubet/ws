package com.lrubstudio.workshape;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

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
    public static final String finished = "finished";
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
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_SQLite);
        else
            request = context.getString(R.string.request_product);
        return request.replaceAll("#qr_code#", qrCode);
    }

    static public String buildRequestProductList(Context context)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_list_SQLite);
        else
            request = context.getString(R.string.request_product_list);
        return request;
    }

    static public String buildRequestEventList(Context context, String qrCode)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_event_list_SQLite);
        else
            request = context.getString(R.string.request_event_list);
        return request.replaceAll("#qr_code#", qrCode);
    }

    static public String buildRequestProductIn(Context context, String qrCode, String date,
                                               String longueurConsommee, String finished, String temps_hors_gel,
                                               String string_temps_hors_gel, String lieuActuel,
                                               String event_label, String lieuActuelLabel,
                                               String LongueurConsommeeLabel, String tempsHorsGelLabel)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_in_SQLite);
        else
            request = context.getString(R.string.request_product_in);

        request = request.replaceAll("#qr_code#", qrCode);
        request = request.replaceAll("#date#", date);
        request = request.replaceAll("#longueur_consommee#", longueurConsommee);
        request = request.replaceAll("#finished#", finished);
        request = request.replaceAll("#temps_hors_gel#", temps_hors_gel);
        request = request.replaceAll("#string_temps_hors_gel#", string_temps_hors_gel);
        request = request.replaceAll("#lieu_actuel#", lieuActuel);
        request = request.replaceAll("#event_label#", event_label);
        request = request.replaceAll("#lieu_actuel_label#", lieuActuelLabel);
        request = request.replaceAll("#longueur_consommee_label#", LongueurConsommeeLabel);
        request = request.replaceAll("#temps_hors_gel_label#", tempsHorsGelLabel);

        return request;
    }

    static public String buildRequestProductOut(Context context, String qrCode, String date, String storedSince, String currentLength, String totalHorsGel,
                                                String eventLabel, String enStockDepuisLabel, String longueurActuelleLabel, String stringTotalHorsGelLabel)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_out_SQLite);
        else
            request = context.getString(R.string.request_product_out);

        request = request.replaceAll("#qr_code#", qrCode);
        request = request.replaceAll("#date#", date);
        request = request.replaceAll("#en_stock_depuis#", storedSince);
        request = request.replaceAll("#longueur_actuelle#", currentLength);
        request = request.replaceAll("#total_hors_gel#", totalHorsGel);
        request = request.replaceAll("#event_label#", eventLabel);
        request = request.replaceAll("#en_stock_depuis_label#", enStockDepuisLabel);
        request = request.replaceAll("#longueur_actuelle_label#", longueurActuelleLabel);
        request = request.replaceAll("#string_total_hors_gel_label#", stringTotalHorsGelLabel);

        return request;
    }

    static public String buildRequestProductAdd(Context context, String qrCode, String date,
                                                String name, String fournisseur, String refFournisseur,
                                                String longueurInitiale, String largeur, String grammage,
                                                String typeDeTissus, String dateArrivee, String transportFrigo,
                                                String lieuActuel, String finished, String event_label, String name_label)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_add_SQLite);
        else
            request = context.getString(R.string.request_product_add);

        request = request.replaceAll("#qr_code#", qrCode);
        request = request.replaceAll("#date#", date);
        request = request.replaceAll("#name#", name);
        request = request.replaceAll("#fournisseur#", fournisseur);
        request = request.replaceAll("#ref_fournisseur#", refFournisseur);
        request = request.replaceAll("#longueur_initiale#", longueurInitiale);
        request = request.replaceAll("#largeur#", largeur);
        request = request.replaceAll("#grammage#", grammage);
        request = request.replaceAll("#type_de_tissus#", typeDeTissus);
        request = request.replaceAll("#datarrivee#", dateArrivee);
        request = request.replaceAll("#transport_frigo#", transportFrigo);
        request = request.replaceAll("#lieu_actuel#", lieuActuel);
        request = request.replaceAll("#finished#", finished);
        request = request.replaceAll("#event_label#", event_label);
        request = request.replaceAll("#name_label#", name_label);
        return request;
    }

    static public String buildRequestProductUpdate(Context context, String qrCode, String date,
                                                String name, String fournisseur, String refFournisseur,
                                                String longueurInitiale, String largeur, String grammage,
                                                String typeDeTissus, String dateArrivee, String transportFrigo,
                                                String lieuActuel, String finished, String eventLabel)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_update_SQLite);
        else
            request = context.getString(R.string.request_product_update);

        request = request.replaceAll("#qr_code#", qrCode);
        request = request.replaceAll("#date#", date);
        request = request.replaceAll("#name#", name);
        request = request.replaceAll("#fournisseur#", fournisseur);
        request = request.replaceAll("#ref_fournisseur#", refFournisseur);
        request = request.replaceAll("#longueur_initiale#", longueurInitiale);
        request = request.replaceAll("#largeur#", largeur);
        request = request.replaceAll("#grammage#", grammage);
        request = request.replaceAll("#type_de_tissus#", typeDeTissus);
        request = request.replaceAll("#datarrivee#", dateArrivee);
        request = request.replaceAll("#transport_frigo#", transportFrigo);
        request = request.replaceAll("#lieu_actuel#", lieuActuel);
        request = request.replaceAll("#finished#", finished);
        request = request.replaceAll("#event_label#", eventLabel);
        return request;
    }

    static public String buildRequestProductUpdateNote(Context context, String qrCode, String date, String note, String eventLabel)
    {
        String request;
        if (ConfigurationActivity.configuration.localDb)
            request = context.getString(R.string.request_product_update_note_SQLite);
        else
            request = context.getString(R.string.request_product_update_note);

        request = request.replaceAll("#qr_code#", qrCode);
        request = request.replaceAll("#date#", date);
        request = request.replaceAll("#note#", note);
        request = request.replaceAll("#event_label#", eventLabel);
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

    public boolean isProductOut()
    {
        // product is considered out if 'lieuActuel' is null or empty
        String value = get(DbProduct.lieuActuel);
        if (value != null && !value.isEmpty())
            return false;
        return true;
    }

    public void fillFragmentFromFields(View fragment, int[] edits, String[] dbfields)
    {
        int text_id = 0;
        for (int id : edits)
        {
            try
            {
                // db value
                String value = this.get(dbfields[text_id]);

                // set text of EditText or Button widget
                View view = fragment.findViewById(id);
                if (view instanceof EditText)
                    ((EditText)view).setText(value);
                else if (view instanceof Switch)
                    ((Switch)view).setChecked(value.equals("1"));
                else if (view instanceof Button)
                    ((Button)view).setText(value);
            }
            catch(Exception eEdit)
            {
                // nothing
            }
            text_id += 1;
        }
    }

    public void fillFieldsFromFragment(View fragment, int[] edits, String[] dbfields)
    {
        int text_id = 0;
        for (int id : edits)
        {
            try
            {
                // EditText or Button text
                String value = null;
                View view = fragment.findViewById(id);
                if (view instanceof EditText)
                    value = ((EditText)view).getText().toString();
                else if (view instanceof Switch)
                    value = ((Switch)view).isChecked() ? "1" : "";
                else if (view instanceof Button)
                    value = ((Button)view).getText().toString();

                // fill db field from widget value
                if (value != null)
                    this.set(dbfields[text_id], value);
            }
            catch(Exception eEdit)
            {
                // nothing
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

    public void set(String fieldName, String fieldValue)
    {
        try
        {
            if (mapArray.get(0).containsKey(fieldName))
                mapArray.get(0).put(fieldName, fieldValue);
        }
        catch (Exception e)
        {
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
