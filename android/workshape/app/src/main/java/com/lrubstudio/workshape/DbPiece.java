package com.lrubstudio.workshape;

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
    public final String qrcode = "qrcode";
    public final String fournisseur = "fournisseur";
    public final String reffournisseur = "reffournisseur";
    public final String longueurinitiale = "longueurinitiale";
    public final String longueuractuelle= "longueuractuelle";
    public final String largeur = "largeur";
    public final String grammage = "grammage";
    public final String typedetissus = "typedetissus";
    public final String datedarrivee = "datedarrivee";
    public final String transportfrigo = "transportfrigo";
    public final String decongelele = "decongelele";
    public final String decongele = "decongele";

    // db values (input field name, output value)
    private Map map;

    public void setFromMap(Map map)
    {
        this.map = map;
    }

    static public Map setDbgValues()
    {
        Map tmp_map = null;

        try
        {
            tmp_map = new HashMap();
            tmp_map.put("idproduct", "123456");
            tmp_map.put("reference", "product ref");
            tmp_map.put("qrcode", "abcd1234");
            tmp_map.put("fournisseur", "Fournisseur");
            tmp_map.put("reffournisseur", "Ref Fournisseur");
            tmp_map.put("longueurinitiale", "25.00");
            tmp_map.put("longueuractuelle", "7.50");
            tmp_map.put("largeur", "1.25");
            tmp_map.put("grammage", "1 kg / m2");
            tmp_map.put("typedetissus", "Sergé");
            tmp_map.put("datedarrivee", "1 janvier 2016");
            tmp_map.put("transportfrigo", "Oui");
            tmp_map.put("decongelele", "2 juin 2016 11h00");
            tmp_map.put("decongele", "4 fois");
        }
        finally
        {
        }

        return tmp_map;
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
