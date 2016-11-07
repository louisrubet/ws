package com.lrubstudio.workshape;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by louis on 02/11/16.
 */

public class DbRequestSQLite extends DbRequest implements SQLiteDatabase.CursorFactory
{
    SQLiteDatabase database;
    boolean isCommand = false;

    // construction
    public DbRequestSQLite(AsyncResponse delegate, String requestName)
    {
        super(delegate, requestName);

        // open
        try
        {
            database = openOrCreateDatabase("/data/data/com.lrubstudio.workshape/local.db", this, null);
            createStructure();
        }
        catch (Exception e)
        {
        }
    }

    public DbRequestSQLite(AsyncResponse delegate, String requestName, boolean isCommand)
    {
        super(delegate, requestName);
        this.isCommand = isCommand;

        // open
        try
        {
            database = openOrCreateDatabase("/data/data/com.lrubstudio.workshape/local.db", this, null);
            createStructure();
        }
        catch (Exception e)
        {
        }
    }

    public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query)
    {
        return new SQLiteCursor(db, masterQuery, editTable, query);
    }

    private void createStructure()
    {
        try
        {
            database.execSQL("CREATE TABLE IF NOT EXISTS product(" +
                    "qr_code VARCHAR(45)," +
                    "name VARCHAR(45)," +
                    "fournisseur VARCHAR(45)," +
                    "ref_fournisseur VARCHAR(45)," +
                    "longueur_initiale DECIMAL(10,2)," +
                    "longueur_actuelle DECIMAL(10,2)," +
                    "largeur DECIMAL(10,2)," +
                    "grammage VARCHAR(45)," +
                    "type_de_tissus VARCHAR(45)," +
                    "date_arrivee DATETIME," +
                    "transport_frigo VARCHAR(10)," +
                    "lieu_actuel VARCHAR(45)," +
                    "lieu_depuis DATETIME," +
                    "temps_hors_gel_total INT(11)," +
                    "nb_decongelation INT(11)," +
                    "note TEXT" +
                    ");");
            database.execSQL("CREATE TABLE IF NOT EXISTS event(" +
                    "idevent INT(11)," +
                    "qr_code VARCHAR(45)," +
                    "date DATETIME," +
                    "champ1 VARCHAR(45)," +
                    "valeur1 VARCHAR(45)," +
                    "champ2 VARCHAR(45)," +
                    "valeur2 VARCHAR(45)," +
                    "champ3 VARCHAR(45)," +
                    "valeur3 VARCHAR(45)" +
                    ");");
        }
        catch (Exception e)
        {
        }
    }

    // AsyncTask extents
    protected ArrayList<Map> doInBackground(String... request)
    {
        if (isCommand)
            return doCommand(request);
        else
            return doRequest(request);
    }

    protected ArrayList<Map> doRequest(String... request)
    {
        ArrayList<Map> mapList = null;

        try
        {
            // execute query
            Cursor cursor = database.rawQuery(request[0], null, null);
            if (cursor != null)
            {
                int columnsCount = cursor.getColumnCount();
                if (columnsCount <= 0)
                    throw(new DbRequestException(DBERR_OK));

                // make result array
                int row = 0;

                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    if (mapList == null)
                        mapList = new ArrayList<Map>();
                    if (!mapList.add(new HashMap()))
                        throw(new DbRequestException(DBERR_REQUEST_MEMORY_ERROR));

                    // add columns in result row
                    int i;
                    for (i = 0; i < columnsCount; i++)
                    {
                        String columnName = cursor.getColumnName(i);
                        if (columnName.length() > 0)
                            mapList.get(row).put(columnName, cursor.getString(i));
                        Log.e("DbRequest", "field:" + columnName + " : " + cursor.getString(i));
                    }

                    row++;
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        catch (DbRequestException e)
        {
            lastError = e.dbRequestReason;
            lastErrorString = e.getMessage();
        }
        catch(SQLiteException e)
        {
            lastError = DBERR_REQUEST_ERROR;
            lastErrorString = e.getMessage();
        }
        catch (Exception e)
        {
            lastError = DBERR_CONNECTION_FAILED;
            lastErrorString = e.getMessage();
        }

        return mapList;
    }

    protected ArrayList<Map> doCommand(String... request)
    {
        try
        {
            // execute command
            database.execSQL(request[0]);
        }
        catch(SQLiteException e)
        {
            lastError = DBERR_REQUEST_ERROR;
            lastErrorString = e.getMessage();
        }
        catch (Exception e)
        {
            lastError = DBERR_CONNECTION_FAILED;
            lastErrorString = e.getMessage();
        }

        return null;
    }
}