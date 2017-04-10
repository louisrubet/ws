package com.lrubstudio.workshape;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQuery;
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

    static String initDbCommand = null;
    static boolean initDbCommandDone = false;

    // set the command to construct once the database
    public static void setInitDbCommand(String initDbCommand)
    {
        DbRequestSQLite.initDbCommand = initDbCommand;
    }

    // construction
    public DbRequestSQLite(AsyncResponse delegate, String requestName)
    {
        super(delegate, requestName);

        // open
        try
        {
            // open or create and open
            database = openOrCreateDatabase("/data/data/com.lrubstudio.workshape/local.db", this, null);

            // init once
            if (!DbRequestSQLite.initDbCommandDone && DbRequestSQLite.initDbCommand != null)
                doInitDb();
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
            // open or create and open
            database = openOrCreateDatabase("/data/data/com.lrubstudio.workshape/local.db", this, null);

            // init once
            if (!DbRequestSQLite.initDbCommandDone && DbRequestSQLite.initDbCommand != null)
                doInitDb();
        }
        catch (Exception e)
        {
        }
    }

    public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query)
    {
        return new SQLiteCursor(db, masterQuery, editTable, query);
    }

    //
    private void doInitDb()
    {
        try
        {
            // request is a string array containing only 1 entry
            // request[0] is a string eventually containing several commands
            // separated by ";"
            String[] commands = DbRequestSQLite.initDbCommand.split(";");
            for (int i = 0; i < commands.length; i++)
            {
                commands[i] = commands[i].concat(";");
                database.execSQL(commands[i]);
            }
            initDbCommandDone = true;
        }
        catch(SQLiteException e)
        {
            lastError = Error.request_error;
            lastErrorString = e.getMessage();
        }
        catch (Exception e)
        {
            lastError = Error.connection_failed;
            lastErrorString = e.getMessage();
        }
    }

    // AsyncTask extents
    @Override
    protected ArrayList<Map> doInBackground(String... request)
    {
        if (isCommand)
        {
            // request is a string array containing only 1 entry
            // request[0] is a string eventually containing several commands
            // separated by ";"
            String[] commands = request[0].split(";");
            for (int i = 0; i < commands.length; i++)
                commands[i] = commands[i].concat(";");
            return doCommand(commands);
        }
        else
            return doRequest(request);
    }

    private ArrayList<Map> doRequest(String... request)
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
                    throw(new DbRequestException(Error.ok));

                // make result array
                int row = 0;

                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    if (mapList == null)
                        mapList = new ArrayList<Map>();
                    if (!mapList.add(new HashMap()))
                        throw(new DbRequestException(Error.request_memory_error));

                    // add columns in result row
                    int i;
                    for (i = 0; i < columnsCount; i++)
                    {
                        String columnName = cursor.getColumnName(i);
                        if (columnName.length() > 0)
                            mapList.get(row).put(columnName, cursor.getString(i));
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
            lastError = Error.request_error;
            lastErrorString = e.getMessage();
        }
        catch (Exception e)
        {
            lastError = Error.connection_failed;
            lastErrorString = e.getMessage();
        }

        return mapList;
    }

    private ArrayList<Map> doCommand(String... commands)
    {
        try
        {
            // execute command
            for (String command : commands)
                database.execSQL(command);
        }
        catch(SQLiteException e)
        {
            lastError = Error.request_error;
            lastErrorString = e.getMessage();
        }
        catch (Exception e)
        {
            lastError = Error.connection_failed;
            lastErrorString = e.getMessage();
        }

        return null;
    }
}