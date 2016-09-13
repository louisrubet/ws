package com.lrubstudio.workshape;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * Created by louis on 26/04/16.
 */
public class SyncDialog
{
    private boolean resultValue;

    public boolean run(Context context, String title, String message, String true_message, String false_message)
    {
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message mesg)
            {
                throw new RuntimeException();
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);

        alert.setPositiveButton(true_message, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                resultValue = true;
                handler.sendMessage(handler.obtainMessage());
            }
        });

        if (false_message.length() > 0)
        {
            alert.setNegativeButton(false_message, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    resultValue = false;
                    handler.sendMessage(handler.obtainMessage());
                }
            });
        }
        alert.show();

        try
        {
            Looper.loop();
        }
        catch(RuntimeException e)
        {
        }

        return resultValue;
    }
}
