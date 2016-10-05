package com.lrubstudio.workshape;

/**
 * Created by louis on 05/10/16.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment implements View.OnClickListener, DbRequest.AsyncResponse
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        if (view != null)
        {

        }
        return view;
    }

    @Override
    public void onClick(View view)
    {
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
    }
}
