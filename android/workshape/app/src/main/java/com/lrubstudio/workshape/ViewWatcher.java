package com.lrubstudio.workshape;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by louis on 21/01/17.
 */

public class ViewWatcher implements TextWatcher
{
    private DataFragment _dataFragment;
    private View _view;
    private String _originalString;

    public ViewWatcher(DataFragment dataFragment, View view, String originalString)
    {
        _dataFragment = dataFragment;
        _view = view;
        _originalString = originalString;
    }

    static void AddMultipleWidgetsToWatcher(DataFragment dataFragment, View parentView, int[] ids)
    {
        try
        {
            for (int id : ids)
            {
                View View = parentView.findViewById(id);
                if (View != null)
                {
                    if (View instanceof EditText)
                        ((EditText) View).addTextChangedListener(new ViewWatcher(dataFragment, ((EditText) View), ((EditText) View).getText().toString()));
                    else if (View instanceof Button)
                        ((Button) View).addTextChangedListener(new ViewWatcher(dataFragment, ((Button) View), ((Button) View).getText().toString()));
                }
            }
        }
        catch(Exception e)
        {
            // nothing
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    public void onTextChanged(CharSequence s, int start, int before, int count) { }
    public void afterTextChanged(Editable s)
    {
        boolean modified = false;

        // check this View was modified
        if (_view instanceof EditText)
            modified = !_originalString.equals(((EditText)_view).getText().toString());
        else if (_view instanceof Button)
            modified = !_originalString.equals(((Button)_view).getText().toString());

        // yes, call DataFragment back
        if (modified)
            _dataFragment.wasModified();
    }
}
