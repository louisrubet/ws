package com.lrubstudio.workshape;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InOutFragment.OnInOutInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InOutFragment extends Fragment
{
    // mode (add or edit)
    public static final int MODE_NONE = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;
    private int mode = MODE_NONE;

    private OnInOutInteractionListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View ret;
        ret = inflater.inflate(R.layout.fragment_in_out, container, false);
        if (ret != null && listener != null)
            // activity callback
            listener.onInOutCreateView(ret);
        return ret;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnInOutInteractionListener)
        {
            listener = (OnInOutInteractionListener)context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnInOutInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

    public interface OnInOutInteractionListener
    {
        void onInOutCreateView(View view);
    }
}
