package com.lrubstudio.workshape;

import android.content.Context;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by louis on 30/10/16.
 */

public class StringValidation
{
    public static boolean EditTextShouldBeDecimal(EditText edit)
    {
        boolean ret = false;
        if (edit != null)
        {
            String entry_string = edit.getText().toString();

            try
            {
                float entry_float = Float.parseFloat(entry_string);
                edit.setError(null);
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                ret =true;
            }
            catch(Exception e)
            {
                edit.setError(edit.getResources().getString(R.string.should_be_decimal));
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_24dp, 0);
            }
        }

        return ret;
    }

    public static boolean EditTextShouldBeHHmm(EditText edit)
    {
        boolean ret = false;
        if (edit != null)
        {
            String entry_string = edit.getText().toString();

            try
            {
                DateFormat df = new SimpleDateFormat("HH:mm");
                Date result =  df.parse(edit.getText().toString());
                edit.setError(null);
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                ret =true;
            }
            catch(Exception e)
            {
                edit.setError(edit.getResources().getString(R.string.should_be_hhmm));
                edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_24dp, 0);
            }
        }

        return ret;
    }

}
