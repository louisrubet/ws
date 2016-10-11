package com.lrubstudio.workshape;

/**
 * Created by louis on 06/10/16.
 */

public final class Debug
{
    // db simulation
    public static boolean NO_DB()
    {
        if (ConfigurationActivity.configuration.dbg_no_db.isEmpty() || ConfigurationActivity.configuration.dbg_no_db == "0")
            return false;
        return true;
    }

    public static boolean SIMULATE_PRODUCT_NEW()
    {
        if (ConfigurationActivity.configuration.dbg_simulate_product_new.isEmpty() || ConfigurationActivity.configuration.dbg_simulate_product_new == "0")
            return false;
        return true;
    }

    public static boolean SIMULATE_PRODUCT_OUT()
    {
        if (ConfigurationActivity.configuration.dbg_simulate_product_out.isEmpty() || ConfigurationActivity.configuration.dbg_simulate_product_out == "0")
            return false;
        return true;
    }

    public static boolean SIMULATE_PRODUCT_IN()
    {
        if (ConfigurationActivity.configuration.dbg_simulate_product_in.isEmpty() || ConfigurationActivity.configuration.dbg_simulate_product_in == "0")
            return false;
        return true;
    }
}
