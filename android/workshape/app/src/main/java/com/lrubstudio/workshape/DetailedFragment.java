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
 * {@link DetailedFragment.OnDetailedInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedFragment extends Fragment
{
    private OnDetailedInteractionListener listener;

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
        ret = inflater.inflate(R.layout.fragment_detailed, container, false);
        if (ret != null && listener != null)
            // activity callback
            listener.onDetailedCreateView(ret);
        return ret;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnDetailedInteractionListener)
        {
            listener = (OnDetailedInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnDetailedInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

    public interface OnDetailedInteractionListener
    {
        void onDetailedCreateView(View view);
    }
}
