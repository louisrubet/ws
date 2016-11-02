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
    protected int lastError = DBERR_OK;
    protected String lastErrorString;

    // internals
    protected String requestName = null;
    public AsyncResponse delegate = null;

    // default
    public DbRequest() { }

    public DbRequest(AsyncResponse delegate, String requestName)
    {
        this.delegate = delegate;
        this.requestName = requestName;
    }

    // factory
    public static DbRequest createRequest(AsyncResponse delegate, String requestName)
    {
        if (Debug.NO_DB)
            return new DbRequestSQLite(delegate, requestName);
        else
            return new DbRequestMySql(delegate, requestName);
    }

    // to be overloaded
    public String getDriverClass()
    {
        // jtds for mysql
        return null;
    }

    public String getConnectionString()
    {
        return null;
    }

    public Properties getConnectionProperty()
    {
        return null;
    }

    // helper for callers

    // DbRequest external user MUST implement this interface
    public interface AsyncResponse
    {
        void dbRequestFinished(String requestName, ArrayList<Map> result, int dbError, String dbErrorString);
    }

    protected class DbRequestException extends Exception
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
    // implementation classes must create a new new ArrayList<Map> and fill with a list of maps
    // containing couls "column name", "column value", like
    // { {{"column1 name", "value11"} , {"column2 name", "value12"}},
    //   {{"column1 name", "value21"} , {"column2 name", "value22"}}
    protected ArrayList<Map> doInBackground(String... request)
    {
        return null;
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
}
