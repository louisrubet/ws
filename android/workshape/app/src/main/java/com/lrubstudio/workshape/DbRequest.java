package com.lrubstudio.workshape;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by louis on 23/04/16.
 */

public class DbRequest extends AsyncTask<String, Integer, Map>
{
    // debug
    private static final Boolean DBG = false;
    private static int dbgCount = 0;

    // errors
    public static final int DBERR_OK = 0;
    public static final int DBERR_CONNECTION_FAILED = 1;
    public static final int DBERR_WRITE = 2;
    public static final int DBERR_READ = 3;

    // connection internals
    private Connection connection;
    private int lastError = DBERR_OK;
    private String lastErrorString;

    // default
    public DbRequest() { }

    // to be overloaded
    public String getDriverClass()
    {
        // jtds driver for SQLServer
        // return new String("net.sourceforge.jtds.jdbc.Driver");
        // jtds for mysql
        return new String("com.mysql.jdbc.Driver");
    }

    public String getConnectionString()
    {
        // jtds driver for SQLServer
        /* String connectionString = "jdbc:jtds:sqlserver://"
                + ConfigurationActivity.configuration.serverIp
                + ":" + ConfigurationActivity.configuration.serverPort
                + "/" + ConfigurationActivity.configuration.database
                + ";instance=SQLEXPRESS"
                + ";user=" + ConfigurationActivity.configuration.user
                + ";password=" + ConfigurationActivity.configuration.password
                + ";loginTimeout=" + ConfigurationActivity.configuration.connectionTimeoutS
                */
        // jtds driver for mysql
        String connectionString = "jdbc:mysql://"
                + ConfigurationActivity.configuration.serverIp
                + ":" + ConfigurationActivity.configuration.serverPort
                + "/" + ConfigurationActivity.configuration.database;
        return connectionString;
    }

    public Properties getConnectionProperty()
    {
        // exception must be caught by caller

        // jtds driver for SQLServer
        //return new Properties();

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
        void dbRequestFinished(Map result, int dbError, String dbErrorString);
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
            if (connect())
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
        delegate.dbRequestFinished(result, lastError, lastErrorString);
    }

    private boolean connect()
    {
        boolean is_connected;

        if (DBG)
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
                lastErrorString = "";
            }
        }

        return is_connected;
    }
}