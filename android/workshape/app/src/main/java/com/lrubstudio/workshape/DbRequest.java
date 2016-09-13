package com.lrubstudio.workshape;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by louis on 23/04/16.
 */

public class DbRequest extends AsyncTask<String, Integer, Map>
{
    // debug
    private static final Boolean DBG = true;
    private static int dbgCount = 0;

    // errors
    public static final int DBERR_OK = 0;
    public static final int DBERR_CONNECTION_FAILED = 1;
    public static final int DBERR_WRITE = 2;
    public static final int DBERR_READ = 3;

    // constants
    private final String driverClass = "com.mysql.jdbc.Driver";
    private final String connectionHeader = "jdbc:mysql://";

    // connection internals
    private Connection connection;
    private boolean is_connected = false;
    private int lastError = DBERR_OK;

    // helper for callers

    // define an interface for response to caller
    public interface AsyncResponse
    {
        void dbRequestFinished(Map result, int dbError);
    }
    public AsyncResponse delegate = null;

    //
    public DbRequest(AsyncResponse delegate)
    {
        this.delegate = delegate;
    }

    // AsyncTask extents
    protected Map doInBackground(String... request)
    {
        ResultSet set = null;
        Map map = null;
        int columnsCount = 0;

        if (DBG)
        {
            map = DbPiece.setDbgValues();
        }
        else
        {
            // TODO faire un connect systematique ?
            // connect if not done
            if (!is_connected)
                is_connected = connect();

            //
            if (is_connected)
            {
                try
                {
                    // execute query
                    PreparedStatement st = connection.prepareStatement(request[0]);
                    set = st.executeQuery();
                    if (set != null)
                    {
                        // get columns count
                        ResultSetMetaData rsmd = set.getMetaData();
                        if (rsmd != null)
                        {
                            columnsCount = rsmd.getColumnCount();

                            // consider first line ONLY
                            if (columnsCount > 0 && set.first())
                            {
                                // make result array
                                map = new HashMap();

                                // add columns in result
                                int i;
                                for (i = 1; i <= columnsCount; i++)
                                {
                                    String columnName = rsmd.getColumnName(i);
                                    if (columnName.length() > 0)
                                        map.put(columnName, set.getString(i));
                                }
                            }
                            lastError = DBERR_OK;
                        }
                        else
                            lastError = DBERR_READ;
                    }
                    else
                        lastError = DBERR_CONNECTION_FAILED;
                }
                catch (Exception e)
                {
                    lastError = DBERR_CONNECTION_FAILED;
                }
            }
            else
                lastError = DBERR_CONNECTION_FAILED;
        }

        return map;
    }

    protected void onProgressUpdate(Integer... progress)
    {
    }

    protected void onPostExecute(Map result)
    {
        delegate.dbRequestFinished(result, lastError);
    }

    private boolean connect()
    {
        if (DBG)
        {
            is_connected = true;
        }
        else
        {
            try
            {
                Class.forName(driverClass);
                Properties theProperties = new Properties();

                theProperties.setProperty("user", ConfigurationActivity.configuration.user);
                theProperties.setProperty("password", ConfigurationActivity.configuration.password);
                theProperties.setProperty("connectTimeout", ConfigurationActivity.configuration.connectionTimeoutMs);
                connection = DriverManager.getConnection(connectionHeader + ConfigurationActivity.configuration.serverIp + ":" + ConfigurationActivity.configuration.serverPort + "/" + ConfigurationActivity.configuration.database, theProperties);
                is_connected = true;
            }
            catch (Exception e)
            {
                is_connected = false;
            }
        }
        return is_connected;
    }
}
