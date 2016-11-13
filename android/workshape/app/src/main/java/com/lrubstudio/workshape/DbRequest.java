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
    public enum Error
    {
        ok,
        connection_failed,
        request_error,
        request_memory_error,
        unknown
    }

    // connection internals
    protected Error lastError = Error.ok;
    protected String lastErrorString;

    // internals
    protected String requestName = null;
    public AsyncResponse delegate = null;

    // default
    public DbRequest() { }

    // construct
    //
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

    public static DbRequest createCommand(AsyncResponse delegate, String requestName)
    {
        if (Debug.NO_DB)
            return new DbRequestSQLite(delegate, requestName, true);
        else
            return new DbRequestMySql(delegate, requestName);
    }

    // to be overloaded
    //
    public String getDriverClass()
    {
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

    // AsyncTask extents
    // implementation classes must return a new ArrayList<Map> filled with a list of maps
    // containing couples "column name", "column value", like
    // { {{"column1 name", "value11"} , {"column2 name", "value12"}},
    //   {{"column1 name", "value21"} , {"column2 name", "value22"}}
    protected ArrayList<Map> doInBackground(String... request)
    {
        return null;
    }

    // helper for callers
    //

    // DbRequest external user MUST implement this interface
    public interface AsyncResponse
    {
        void dbRequestFinished(String requestName, ArrayList<Map> result, DbRequest.Error dbError, String dbErrorString);
    }

    protected class DbRequestException extends Exception
    {
        DbRequestException(Error reason) { dbRequestReason = reason; }
        public Error dbRequestReason;
    }

    // error category
    public static String errorCategory(Context context, Error error)
    {
        String[] category = { context.getString(R.string.dbrequest_no_error),
                context.getString(R.string.dbrequest_connection_failed),
                context.getString(R.string.dbrequest_request_error),
                context.getString(R.string.dbrequest_memory_error),
                context.getString(R.string.dbrequest_unknown)
        };
        if (error.ordinal() >= 0 && error.ordinal() <= Error.values().length)
            return category[error.ordinal()];
        else
            return category[Error.unknown.ordinal()];
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
