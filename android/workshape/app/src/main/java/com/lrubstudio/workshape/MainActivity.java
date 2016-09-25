package com.lrubstudio.workshape;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements DbRequest.AsyncResponse
{
    // db request for one product and product reference
    private static final String PRODUCT_REQUEST = "select * from product where qrcode=";
    private static String lastPieceReference = "";

    //
    private static DbPiece lastRequestedPiece = new DbPiece();
    public static DbPiece getLastRequestedPiece() { return lastRequestedPiece; }

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // statusbar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        // toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //myToolbar.setLogo(R.drawable.ic_action_barcode_2);

        // round progressbar for db access
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        // put back last piece reference
        ((EditText)findViewById(R.id.editText)).setText(lastPieceReference);

        // read stored configuration (SharedPreference)
        ConfigurationActivity.init_configuration((Context)this);
    }

    @Override
    public void dbRequestFinished(Map result, int dbError, String dbErrorString)
    {
        // stop the turning thing
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        if (dbError == DbRequest.DBERR_OK)
        {
            if (result == null)
            {
                // unknown piece -> show it
                Toast.makeText(this, getResources().getString(R.string.unknown_piece), Toast.LENGTH_SHORT).show();
            }
            else
            {
                // keep values
                lastRequestedPiece.setFromMap(result);

                // -> run edit activity
                Intent intent = new Intent(this, EditAddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mode", EditAddActivity.MODE_EDIT);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else
        {
            // toast db error
            String error;
            switch(dbError)
            {
                case DbRequest.DBERR_CONNECTION_FAILED:
                    if (dbErrorString.isEmpty())
                        error = getResources().getString(R.string.db_err_connection_failed);
                    else
                        error = dbErrorString;
                    break;
                case DbRequest.DBERR_READ:
                    error = getResources().getString(R.string.db_err_read);
                    break;
                case DbRequest.DBERR_WRITE:
                    error = getResources().getString(R.string.db_err_write);
                    break;
                default:
                    error = getResources().getString(R.string.db_err_unknown);
                    break;
            }
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    public void onClickScan(View view)
    {
        // start scanner
        new IntentIntegrator(this).setOrientationLocked(false).setCaptureActivity(ScannerActivity.class).initiateScan();
    }

    public void onClickGo(View view)
    {
        // get info about this reference
        EditText edit = (EditText)findViewById(R.id.editText);
        if (edit != null && edit.length() > 0)
        {
            String qr = edit.getText().toString();
            if (qr.length() > 0)
            {
                // start the turning thing
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                // run asynchronous request
                new DbRequest(this).execute(PRODUCT_REQUEST + "\"" + qr + "\"");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_configuration:
                onClickConfiguration(null);
                return true;

            case R.id.action_add:
                onClickAdd(null);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickAdd(View view)
    {
        // -> run edit activity (cause: add)
        Intent intent = new Intent(this, EditAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("mode", EditAddActivity.MODE_ADD);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickConfiguration(View view)
    {
        // show a dialog asking for password
        if (ConfigurationActivity.configuration.conf_password.length() > 0)
        {
            final MainActivity main = this;
            final EditText edit;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dlg = inflater.inflate(R.layout.dialog_configuration_password, null);
            builder.setView(dlg);

            edit = (EditText) dlg.findViewById(R.id.editConfigurationPassword);
            builder.setTitle(getResources().getString(R.string.conf_password_title));
            builder.setPositiveButton(R.string.enter, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    if (edit.getText().toString().equals(ConfigurationActivity.configuration.conf_password))
                    {
                        // enter configuration activity if password matches
                        Intent intent = new Intent(main, ConfigurationActivity.class);
                        startActivity(intent);
                    }
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    // User cancelled the dialog
                }
            });

            // create dlg, show keyboard, run dlg
            AlertDialog dialog = builder.create();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.show();
        }
        else
        {
            // no password -> enter configuration dialog
            Intent intent = new Intent(this, ConfigurationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // scanner activity result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            // result and not cancelled
            if(result.getContents() != null)
            {
                // keep reference and set it to edit
                lastPieceReference = result.getContents();
                ((EditText)findViewById(R.id.editText)).setText(lastPieceReference);

                // make thing turn
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                // run asynchronous request
                new DbRequest(this).execute(PRODUCT_REQUEST + "\"" + result.getContents() + "\"");
            }
        }
        else
        {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
