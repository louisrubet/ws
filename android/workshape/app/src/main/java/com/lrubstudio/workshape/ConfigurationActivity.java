package com.lrubstudio.workshape;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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
        String lieuParDefaut;
        String confPassword;
        boolean flashParDefaut;
        boolean localDb;
    }

    public static Configuration configuration;
    private static Context context;

    //
    public static void init_configuration(Context context_)
    {
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
                    configuration.serverIp = sharedPref.getString(context.getString(R.string.conf_server_hostname), "192.168.1.13");
                    configuration.serverPort = sharedPref.getString(context.getString(R.string.conf_server_port), "3306");
                    configuration.database = sharedPref.getString(context.getString(R.string.conf_database_name), "workshapedb");
                    configuration.user = sharedPref.getString(context.getString(R.string.conf_database_user), "workshape");
                    configuration.password = sharedPref.getString(context.getString(R.string.conf_database_password), "workshape");
                    configuration.connectionTimeoutS = sharedPref.getString(context.getString(R.string.conf_database_timeout), "5");
                    configuration.lieuParDefaut = sharedPref.getString(context.getString(R.string.conf_lieu_par_defaut), "frigo");
                    configuration.flashParDefaut = sharedPref.getBoolean(context.getString(R.string.conf_flash_par_defaut), true);
                    configuration.confPassword = sharedPref.getString(context.getString(R.string.conf_password), "");
                    configuration.localDb = sharedPref.getBoolean(context.getString(R.string.conf_database_local), false);
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
        int[] idsStrings = {R.id.editServerHostname, R.id.editServerPort,
                R.id.editDatabaseName, R.id.editDatabaseUser,
                R.id.editDatabasePassword, R.id.editDatabaseTimeout,
                R.id.editLieuParDefaut, R.id.editConfigurationPassword };

        String[] entries = new String[] { configuration.serverIp, configuration.serverPort,
                configuration.database, configuration.user,
                configuration.password, configuration.connectionTimeoutS,
                configuration.lieuParDefaut, configuration.confPassword };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        // toolbar and its title
        String title;
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try
        {
            title = getString(R.string.activity_configuration_title_version) + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch(Exception e)
        {
            title = getString(R.string.activity_configuration_title);
        }
        getSupportActionBar().setTitle(title);

        // setup configuration entries in edits
        for (int i = 0; i < idsStrings.length; i++)
            ((EditText)findViewById(idsStrings[i])).setText(entries[i]);

        // setup TextChangedListener handlers on each edit
        for (int i = 0; i < idsStrings.length; i++)
        {
            final String originalString = entries[i];
            final int id = idsStrings[i];

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

        // Booleans
        //
        final Boolean originalFlashParDefaut = configuration.flashParDefaut;
        ((Switch)findViewById(R.id.switchFlashParDefaut)).setChecked(originalFlashParDefaut);
        ((Switch)findViewById(R.id.switchFlashParDefaut)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if (isChecked == originalFlashParDefaut)
                            findViewById(R.id.buttonApply).setVisibility(View.GONE);
                        else
                            findViewById(R.id.buttonApply).setVisibility(View.VISIBLE);
                    }
                }
        );

        final Boolean originalLocalDb = configuration.localDb;
        ((Switch)findViewById(R.id.switchLocalDb)).setChecked(originalLocalDb);
        ((Switch)findViewById(R.id.switchLocalDb)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if (isChecked == originalLocalDb)
                            findViewById(R.id.buttonApply).setVisibility(View.GONE);
                        else
                            findViewById(R.id.buttonApply).setVisibility(View.VISIBLE);
                    }
                }
        );

        // button to save configuration
        findViewById(R.id.buttonApply).setVisibility(View.GONE);

        // DB fields
        setDbStateFromLocalDb(!configuration.localDb);
    }

    public void onClickLocalDb(View view)
    {
        Switch switchh = (Switch) view;
        if (switchh != null)
        {
            // configuration
            configuration.localDb = !switchh.isChecked();
            setDbStateFromLocalDb(configuration.localDb);
        }
    }

    private void setDbStateFromLocalDb(boolean localDb)
    {
        boolean enable = localDb;

        // enable or disable db entries
        findViewById(R.id.editServerHostname).setEnabled(enable);
        findViewById(R.id.editServerPort).setEnabled(enable);
        findViewById(R.id.editDatabaseName).setEnabled(enable);
        findViewById(R.id.editDatabaseUser).setEnabled(enable);
        findViewById(R.id.editDatabasePassword).setEnabled(enable);
        findViewById(R.id.editDatabaseTimeout).setEnabled(enable);
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

                configuration.localDb = ((Switch)findViewById(R.id.switchLocalDb)).isChecked();
                editor.putBoolean(context.getString(R.string.conf_database_local), configuration.localDb);

                configuration.serverIp = ((EditText)findViewById(R.id.editServerHostname)).getText().toString();
                editor.putString(context.getString(R.string.conf_server_hostname), configuration.serverIp);

                configuration.serverPort = ((EditText)findViewById(R.id.editServerPort)).getText().toString();
                editor.putString(context.getString(R.string.conf_server_port), configuration.serverPort);

                configuration.database = ((EditText)findViewById(R.id.editDatabaseName)).getText().toString();
                editor.putString(context.getString(R.string.conf_database_name), configuration.database);

                configuration.user = ((EditText)findViewById(R.id.editDatabaseUser)).getText().toString();
                editor.putString(context.getString(R.string.conf_database_user), configuration.user);

                configuration.password = ((EditText)findViewById(R.id.editDatabasePassword)).getText().toString();
                editor.putString(context.getString(R.string.conf_database_password), configuration.password);

                configuration.connectionTimeoutS = ((EditText)findViewById(R.id.editDatabaseTimeout)).getText().toString();
                editor.putString(context.getString(R.string.conf_database_timeout), configuration.connectionTimeoutS);

                configuration.confPassword = ((EditText)findViewById(R.id.editConfigurationPassword)).getText().toString();
                editor.putString(context.getString(R.string.conf_password), configuration.confPassword);

                configuration.lieuParDefaut = ((EditText)findViewById(R.id.editLieuParDefaut)).getText().toString();
                editor.putString(context.getString(R.string.conf_lieu_par_defaut), configuration.lieuParDefaut);

                configuration.flashParDefaut = ((Switch)findViewById(R.id.switchFlashParDefaut)).isChecked();
                editor.putBoolean(context.getString(R.string.conf_flash_par_defaut), configuration.flashParDefaut);

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
