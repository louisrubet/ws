package com.lrubstudio.workshape;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by louis on 21/01/17.
 */

public class ViewWatcher implements TextWatcher, CompoundButton.OnCheckedChangeListener
{
    private DataFragment _dataFragment;
    private View _view;
    private String _originalString;
    private boolean _currentlyModified;

    public ViewWatcher(DataFragment dataFragment, View view, String originalString)
    {
        _dataFragment = dataFragment;
        _view = view;
        _originalString = originalString;
        _currentlyModified = false;
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
                    else if (View instanceof Switch)
                        ((Switch) View).setOnCheckedChangeListener(new ViewWatcher(dataFragment, null, ((Switch) View).isChecked() ? "1":"0"));
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

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s)
    {
        boolean modified = false;

        // check this View was modified
        if (_view instanceof EditText)
            modified = !_originalString.equals(((EditText)_view).getText().toString());
        else if (_view instanceof Button)
            modified = !_originalString.equals(((Button)_view).getText().toString());

        // call DataFragment back
        if (modified || (!modified && _currentlyModified))
            _dataFragment.wasModified(modified);

        if (modified)
        {
            _currentlyModified = true;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compound, boolean checked)
    {
        boolean modified = !_originalString.equals( checked?"1":"0" );

        // call DataFragment back
        if (modified || (!modified && _currentlyModified))
            _dataFragment.wasModified(modified);

        if (modified)
        {
            _currentlyModified = true;
        }
    }
}
