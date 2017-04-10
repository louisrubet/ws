package com.lrubstudio.workshape;


import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by louis on 21/01/17.
 */

public class DataFragment extends Fragment
{
    // to override
    public void wasModified(boolean modified)
    {
        // this method is called whenever a data was modified by MMI
    }

    public final void hideKeyboard()
    {
        // Check if a view has focus
        try
        {
            View view = getActivity().getCurrentFocus();
            if (view != null)
            {
                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        catch(Exception e)
        {
            // nothing
        }
    }
}
