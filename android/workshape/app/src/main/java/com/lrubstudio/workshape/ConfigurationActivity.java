package com.lrubstudio.workshape;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity
{
    public static class Configuration
    {
        String serverIp;
        String serverPort;
        String database;
        String user;
        String password;
        String connectionTimeoutS;
        String conf_password;
    }

    public static Configuration configuration;
    private static Context context;

    //
    private int[] ids = {R.id.editServerHostname, R.id.editServerPort,
            R.id.editDatabaseName, R.id.editDatabaseUser,
            R.id.editDatabasePassword, R.id.editDatabaseTimeout,
            R.id.editConfigurationPassword};

    //
    public static void init_configuration(Context context_)
    {
        //
        context = context_;

        if (configuration == null)
            configuration = new Configuration();
        if (configuration != null)
        {
            // open and read configuration preferences
            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.conf_file_key),context.MODE_PRIVATE);
            if (sharedPref != null)
            {
                try
                {
                    // TODO suppr valeurs par defaut
                    configuration.serverIp = sharedPref.getString(context.getString(R.string.server_hostname), "192.168.1.14");
                    configuration.serverPort = sharedPref.getString(context.getString(R.string.server_port), "3306");
                    configuration.database = sharedPref.getString(context.getString(R.string.database_name), "workshapedb");
                    configuration.user = sharedPref.getString(context.getString(R.string.database_user), "workshape");
                    configuration.password = sharedPref.getString(context.getString(R.string.database_password), "qqoap8ck");
                    configuration.connectionTimeoutS = sharedPref.getString(context.getString(R.string.database_timeout), "5");
                    configuration.conf_password = sharedPref.getString(context.getString(R.string.conf_password), "");
                }
                catch(Exception e)
                {
                    Toast.makeText(context, context.getString(R.string.conf_file_read_error), Toast.LENGTH_LONG).show();
                }
            }
            else
                Toast.makeText(context, context.getString(R.string.conf_file_read_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String[] entries = new String[] {configuration.serverIp, configuration.serverPort,
                configuration.database, configuration.user,
                configuration.password, configuration.connectionTimeoutS,
                configuration.conf_password};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        // toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setup configuration entries in edits
        for (int i = 0; i < ids.length; i++)
        {
            ((EditText)findViewById(ids[i])).setText(entries[i]);
        }
        findViewById(R.id.buttonApply).setVisibility(View.GONE);

        // setup TextChangedListener handlers on each edit
        for (int i = 0; i < ids.length; i++)
        {
            final String originalString = entries[i];
            final int id = ids[i];

            ((EditText)findViewById(id)).addTextChangedListener(
                    new TextWatcher()
                    {
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                        public void onTextChanged(CharSequence s, int start, int before, int count) { }
                        public void afterTextChanged(Editable s)
                        {
                            String entry = ((EditText)findViewById(id)).getText().toString();
                            if (entry.equals(originalString))
                                findViewById(R.id.buttonApply).setVisibility(View.GONE);
                            else
                                findViewById(R.id.buttonApply).setVisibility(View.VISIBLE);
                        }
                    }
            );
        }
    }

    public void onClickApply(View view)
    {
        // open and write configuration preferences
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.conf_file_key),context.MODE_PRIVATE);
        if (sharedPref != null)
        {
            try
            {
                // save all parameters
                SharedPreferences.Editor editor = sharedPref.edit();

                configuration.serverIp = ((EditText)findViewById(R.id.editServerHostname)).getText().toString();
                editor.putString(context.getString(R.string.server_hostname), configuration.serverIp);

                configuration.serverPort = ((EditText)findViewById(R.id.editServerPort)).getText().toString();
                editor.putString(context.getString(R.string.server_port), configuration.serverPort);

                configuration.database = ((EditText)findViewById(R.id.editDatabaseName)).getText().toString();
                editor.putString(context.getString(R.string.database_name), configuration.database);

                configuration.user = ((EditText)findViewById(R.id.editDatabaseUser)).getText().toString();
                editor.putString(context.getString(R.string.database_user), configuration.user);

                configuration.password = ((EditText)findViewById(R.id.editDatabasePassword)).getText().toString();
                editor.putString(context.getString(R.string.database_password), configuration.password);

                configuration.connectionTimeoutS = ((EditText)findViewById(R.id.editDatabaseTimeout)).getText().toString();
                editor.putString(context.getString(R.string.database_timeout), configuration.connectionTimeoutS);

                configuration.conf_password = ((EditText)findViewById(R.id.editConfigurationPassword)).getText().toString();
                editor.putString(context.getString(R.string.conf_password), configuration.conf_password);

                editor.commit();

                // make button disappear
                Toast.makeText(context, context.getString(R.string.conf_file_write_done), Toast.LENGTH_SHORT).show();
                findViewById(R.id.buttonApply).setVisibility(View.GONE);

                // exit
                finish();
            }
            catch(Exception e)
            {
                Toast.makeText(context, context.getString(R.string.conf_file_write_error), Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(context, context.getString(R.string.conf_file_write_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
