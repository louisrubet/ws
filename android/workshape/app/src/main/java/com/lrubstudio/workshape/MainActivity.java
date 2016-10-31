package com.lrubstudio.workshape;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DbRequest.AsyncResponse
{
    private static final int ListActivityId = 0;

    //
    private static DbProduct lastRequestedProduct = new DbProduct();
    public static DbProduct getLastRequestedProduct() { return lastRequestedProduct; }

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar and title
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //myToolbar.setLogo(R.drawable.ic_action_barcode_2);

        // hide floating button
        ((FloatingActionButton)findViewById(R.id.fab)).hide();

        // show edit button
        findViewById(R.id.imageButton).setVisibility(View.VISIBLE);

        // managing floating action add button
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((MainActivity)view.getContext()).onClickAdd(view);
            }
        });

        // round progressbar for db access
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        // read stored configuration (SharedPreference)
        ConfigurationActivity.init_configuration((Context)this);

        // put back last product reference
        final EditText editQrCode = (EditText)findViewById(R.id.editText);
        editQrCode.setText(lastRequestedProduct.getQrCode());
        editQrCode.addTextChangedListener(
                new TextWatcher()
                {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    public void afterTextChanged(Editable s)
                    {
                        // hide floating add button in case of text change
                        ((FloatingActionButton)findViewById(R.id.fab)).hide();

                        // show edit button
                        findViewById(R.id.imageButton).setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    @Override
    public void dbRequestFinished(ArrayList<Map> result, int dbError, String dbErrorString)
    {
        try
        {
            // stop the turning thing
            findViewById(R.id.progressBar).setVisibility(View.GONE);

            if (dbError == DbRequest.DBERR_OK)
            {
                if (result == null)
                {
                    // show floating button
                    ((FloatingActionButton) findViewById(R.id.fab)).show();

                    // hide edit button
                    findViewById(R.id.imageButton).setVisibility(View.GONE);

                    // request ok but no result
                    // -> hide keyboard
                    // -> ask user for new product
                    View view = this.getCurrentFocus();
                    if (view != null)
                    {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    Toast.makeText(this, getResources().getString(R.string.produit_inconnu), Toast.LENGTH_LONG).show();
                }
                else
                {
                    // fill db structure (here lastRequestedProduct.QrCode is already ok)
                    lastRequestedProduct.setFromMap(result);
                    MainActivity.getLastRequestedProduct().setNewQrCode(false);

                    // hide floating button
                    ((FloatingActionButton)findViewById(R.id.fab)).hide();

                    // show edit button
                    findViewById(R.id.imageButton).setVisibility(View.VISIBLE);

                    // -> run edit activity
                    startActivity(new Intent(this, EditAddActivity.class));
                }
            }
            else
            {
                // toast db error
                Toast.makeText(this, DbRequest.errorCategory(this, dbError), Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, DbRequest.errorCategory(this, DbRequest.DBERR_REQUEST_ERROR), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickScan(View view)
    {
        // start scanner
        new IntentIntegrator(this).setOrientationLocked(false).setCaptureActivity(ScannerActivity.class).initiateScan();
    }

    public void onClickList(View view)
    {
        // start list activity
        startActivityForResult(new Intent(this, ListActivity.class), ListActivityId);
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
                // keep this qr code
                MainActivity.getLastRequestedProduct().setQrCode(qr);

                // start the turning thing
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                // build request and run asynchronous request
                new DbRequest(this).execute(DbProduct.buildRequestProductView(this, qr));
            }
        }
    }

    public void onClickAdd(View view)
    {
        // hide floating button
        ((FloatingActionButton)findViewById(R.id.fab)).hide();
        // show edit button
        findViewById(R.id.imageButton).setVisibility(View.VISIBLE);

        // read new qr code
        EditText edit = (EditText)findViewById(R.id.editText);
        if (edit != null && edit.length() > 0)
        {
            String qr = edit.getText().toString();
            if (qr.length() > 0)
            {
                // keep this qr code
                MainActivity.getLastRequestedProduct().setQrCode(qr);

                // set it as new
                MainActivity.getLastRequestedProduct().setNewQrCode(true);

                // -> run edit activity (cause: add)
                Intent intent = new Intent(this, EditAddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mode", EditAddActivity.MODE_ADD);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void onClickConfiguration(View view)
    {
        // show a dialog asking for password
        if (ConfigurationActivity.configuration.confPassword.length() > 0)
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
                    if (edit.getText().toString().equals(ConfigurationActivity.configuration.confPassword))
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
        //
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            // result and not cancelled
            if(result.getContents() != null && !result.getContents().isEmpty())
            {
                // keep qrCode and set it to edit
                MainActivity.getLastRequestedProduct().setQrCode(result.getContents());
                ((EditText)findViewById(R.id.editText)).setText(result.getContents());

                // make thing turn
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                // run asynchronous request
                new DbRequest(this).execute(DbProduct.buildRequestProductView(this, result.getContents()));
            }
        }
        // list activity result
        //
        else if (requestCode == ListActivityId)
        {
            try
            {
                // keep qrCode and set it to edit
                String qrCode = data.getStringExtra(DbProduct.qrCode);
                MainActivity.getLastRequestedProduct().setQrCode(qrCode);
                ((EditText) findViewById(R.id.editText)).setText(qrCode);

                // make thing turn
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                // run asynchronous request
                new DbRequest(this).execute(DbProduct.buildRequestProductView(this, qrCode));
            }
            catch(Exception e)
            {
                // could happen if list activity is cancelled
            }
        }
        else
        {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
