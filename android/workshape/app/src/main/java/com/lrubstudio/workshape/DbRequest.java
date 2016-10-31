package com.lrubstudio.workshape;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by louis on 23/04/16.
 */

public class DbRequest extends AsyncTask<String, Integer, ArrayList<Map>>
{
    // errors
    public static final int DBERR_OK = 0;
    public static final int DBERR_CONNECTION_FAILED = 1;
    public static final int DBERR_REQUEST_ERROR = 2;
    public static final int DBERR_REQUEST_MEMORY_ERROR = 3;
    public static final int DBERR_UNKNOWN = 4;

    // connection internals
    private Connection connection;
    private int lastError = DBERR_OK;
    private String lastErrorString;

    // internals
    private String requestName = null;
    public AsyncResponse delegate = null;

    // default
    public DbRequest() { }
    public DbRequest(AsyncResponse delegate, String requestName)
    {
        this.delegate = delegate;
        this.requestName = requestName;
    }

    // to be overloaded
    public String getDriverClass()
    {
        // jtds for mysql
        return new String("com.mysql.jdbc.Driver");
    }

    public String getConnectionString()
    {
        // jtds driver for mysql
        String connectionString = "jdbc:mysql://"
                + ConfigurationActivity.configuration.serverIp
                + ":" + ConfigurationActivity.configuration.serverPort
                + "/" + ConfigurationActivity.configuration.database;
        return connectionString;
    }

    public Properties getConnectionProperty()
    {
         // jtds driver for mysql
        Integer timeoutMs = Integer.parseInt(ConfigurationActivity.configuration.connectionTimeoutS) * 1000;
        Properties prop = new Properties();
        prop.setProperty("user", ConfigurationActivity.configuration.user);
        prop.setProperty("password", ConfigurationActivity.configuration.password);
        prop.setProperty("connectTimeout", String.valueOf(timeoutMs));
        return prop;
    }

    // helper for callers

    // DbRequest external user MUST implement this interface
    public interface AsyncResponse
    {
        void dbRequestFinished(String requestName, ArrayList<Map> result, int dbError, String dbErrorString);
    }

    private class DbRequestException extends Exception
    {
        DbRequestException(int reason) { dbRequestReason = reason; }
        public int dbRequestReason;
    }

    // error category
    public static String errorCategory(Context context, int error)
    {
        String[] category = { context.getString(R.string.dbrequest_no_error),
                context.getString(R.string.dbrequest_connection_failed),
                context.getString(R.string.dbrequest_request_error),
                context.getString(R.string.dbrequest_memory_error),
                context.getString(R.string.dbrequest_unknown)
        };
        if (error >= DBERR_OK && error <= DBERR_UNKNOWN)
            return category[error];
        else
            return category[DBERR_UNKNOWN];
    }

    // AsyncTask extents
    protected ArrayList<Map> doInBackground(String... request)
    {
        ResultSet set = null;
        ArrayList<Map> mapList = null;
        int columnsCount = 0;

        if (Debug.NO_DB)
        {
            if (Debug.SIMULATE_PRODUCT_NEW)
            {
                mapList = DbProduct.setDbgValuesNew();
            }
            else if (Debug.SIMULATE_PRODUCT_OUT)
            {
                mapList = DbProduct.setDbgValuesOut();
            }
            else if (Debug.SIMULATE_PRODUCT_IN)
            {
                mapList = DbProduct.setDbgValuesIn();
            }
        }
        else
        {
            if (connect())
            {
                try
                {
                    // execute query
                    PreparedStatement st = connection.prepareStatement(request[0]);
                    set = st.executeQuery();
                    if (set == null)
                        throw (new DbRequestException(DBERR_CONNECTION_FAILED));

                    // there must be columns and rows
                    ResultSetMetaData rsmd = set.getMetaData();
                    if (rsmd == null)
                        throw(new DbRequestException(DBERR_REQUEST_ERROR));

                    // no entry ? ok but result is null
                    columnsCount = rsmd.getColumnCount();
                    if (columnsCount <= 0 || !set.first())
                        throw(new DbRequestException(DBERR_OK));

                    // make result array
                    int row = 0;
                    mapList = new ArrayList<Map>();
                    set.beforeFirst();

                    while(set.next())
                    {
                        if (!mapList.add(new HashMap()))
                            throw(new DbRequestException(DBERR_REQUEST_MEMORY_ERROR));

                        // add columns in result row
                        int i;
                        for (i = 1; i <= columnsCount; i++)
                        {
                            String columnName = rsmd.getColumnName(i);
                            if (columnName.length() > 0)
                                mapList.get(row).put(columnName, set.getString(i));
                            Log.e("DbRequest", "field:" + columnName + " : " + set.getString(i));
                        }

                        row++;
                    }
                }
                catch(DbRequestException e)
                {
                    lastError = e.dbRequestReason;
                    lastErrorString = e.getMessage();
                }
                catch (Exception e)
                {
                    lastError = DBERR_CONNECTION_FAILED;
                    lastErrorString = e.getMessage();
                }
            }
            else
                lastError = DBERR_CONNECTION_FAILED;
        }

        return mapList;
    }

    protected void onProgressUpdate(Integer... progress)
    {
        // nothing
    }

    protected void onPostExecute(ArrayList<Map> result)
    {
        // call request callback set by caller
        delegate.dbRequestFinished(requestName, result, lastError, lastErrorString);
    }

    private boolean connect()
    {
        boolean is_connected;

        if (Debug.NO_DB)
        {
            is_connected = true;
        }
        else
        {
            try
            {
                Class.forName(getDriverClass());
                connection = DriverManager.getConnection(getConnectionString(), getConnectionProperty());
                is_connected = true;
            }
            catch (SQLException e)
            {
                is_connected = false;
                lastErrorString = e.toString();
            }
            catch (Exception e)
            {
                is_connected = false;
                lastErrorString = e.toString();
            }
        }

        return is_connected;
    }
}
