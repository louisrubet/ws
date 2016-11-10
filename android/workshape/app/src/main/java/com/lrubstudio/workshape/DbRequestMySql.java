package com.lrubstudio.workshape;

import android.util.Log;

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
 * Created by louis on 01/11/16.
 */

public class DbRequestMySql extends DbRequest
{
    private Connection connection;

    // construction
    public DbRequestMySql(AsyncResponse delegate, String requestName)
    {
        super(delegate, requestName);
    }

    @Override
    public String getDriverClass()
    {
        // jtds for mysql
        return new String("com.mysql.jdbc.Driver");
    }

    @Override
    public String getConnectionString()
    {
        // jtds driver for mysql
        String connectionString = "jdbc:mysql://"
                + ConfigurationActivity.configuration.serverIp
                + ":" + ConfigurationActivity.configuration.serverPort
                + "/" + ConfigurationActivity.configuration.database;
        return connectionString;
    }

    @Override
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

    // AsyncTask extents
    @Override
    protected ArrayList<Map> doInBackground(String... request)
    {
        ResultSet set = null;
        ArrayList<Map> mapList = null;
        int columnsCount = 0;

        if (connect())
        {
            try
            {
                // execute query (first string only)
                PreparedStatement st = connection.prepareStatement(request[0]);
                set = st.executeQuery();
                if (set == null)
                    throw (new DbRequestException(Error.connection_failed));

                // there must be columns and rows
                ResultSetMetaData rsmd = set.getMetaData();
                if (rsmd == null)
                    throw(new DbRequestException(Error.request_error));

                // no entry ? ok but result is null
                columnsCount = rsmd.getColumnCount();
                if (columnsCount <= 0 || !set.first())
                    throw(new DbRequestException(Error.ok));

                // make result array
                int row = 0;
                mapList = new ArrayList<Map>();
                set.beforeFirst();

                while(set.next())
                {
                    if (!mapList.add(new HashMap()))
                        throw(new DbRequestException(Error.request_memory_error));

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

                //TODO
                // clear des objets créés
            }
            catch(DbRequestException e)
            {
                lastError = e.dbRequestReason;
                lastErrorString = e.getMessage();
            }
            catch (Exception e)
            {
                lastError = Error.connection_failed;
                lastErrorString = e.getMessage();
            }
        }
        else
            lastError = Error.connection_failed;

        return mapList;
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
