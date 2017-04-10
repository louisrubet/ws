package com.lrubstudio.workshape;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by louis on 26/04/16.
 */
public class SyncDialog
{
    public interface NoticeSyncDialogListener
    {
        void onSyncDialogChoice(boolean positive);
    }
    NoticeSyncDialogListener listener = null;

    SyncDialog(NoticeSyncDialogListener listener)
    {
        this.listener = listener;
    }

    public void run(Context context, String title, String message, String true_message, String false_message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // message
        builder.setTitle(title);
        builder.setMessage(message);

        // at least one button
        builder.setPositiveButton(true_message, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    if (listener != null)
                        listener.onSyncDialogChoice(true);
                }
            });

        // two buttons if any
        if (false_message.length() > 0)
        {
            builder.setNegativeButton(false_message, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        if (listener != null)
                            listener.onSyncDialogChoice(false);
                    }
                });
        }

        try
        {
            builder.create().show();
        }
        catch(Exception e)
        {
        }
    }
}
