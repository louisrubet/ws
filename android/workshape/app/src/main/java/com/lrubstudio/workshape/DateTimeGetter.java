package com.lrubstudio.workshape;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by louis on 09/10/16.
 */

public class DateTimeGetter implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    // interface to set back date and time
    public interface onDateTimeGetter
    {
        void onDateTimeGetter(int id, String dateTime);
    }

    private onDateTimeGetter dateTimeGetter = null;

    // internals
    private Context context;
    private int id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTimeGetter(Context context, onDateTimeGetter dateTimeGetter, int id, int year, int month, int day, int hour, int minute)
    {
        this.context = context;
        this.dateTimeGetter = dateTimeGetter;
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public DateTimeGetter(Context context, onDateTimeGetter dateTimeGetter, int id, String dateTime)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(context.getResources().getString(R.string.date_format_to_android));
        Date dateLieu;

        this.context = context;
        this.dateTimeGetter = dateTimeGetter;
        this.id = id;

        try
        {
            // parsed argument date
            dateLieu = dateFormat.parse(dateTime);
        }
        catch(Exception e)
        {
            // now
            dateLieu = new Date();
        }
        cal.setTime(dateLieu);

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
    }

    void run()
    {
        (new DatePickerDialog(context, this, year, month, day)).show();
    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

        TimePickerDialog tp = new TimePickerDialog(context, this, hour, minute, true);
        if (tp != null)
        {
            tp.show();
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        // compose date time string according to format in resources
        String dateTime = context.getResources().getString(R.string.date_format_to_android);
        String strMonth;
        String strDay;
        String strHour;
        String strMinute;

        strMonth=String.format("%02d", month + 1);// carefull, month is 0..11
        strDay=String.format("%02d", day);
        strHour=String.format("%02d", hourOfDay);
        strMinute=String.format("%02d", minute);

        dateTime = dateTime.replaceAll("yyyy", String.valueOf(year));
        dateTime = dateTime.replaceAll("MM", strMonth);
        dateTime = dateTime.replaceAll("dd", strDay);
        dateTime = dateTime.replaceAll("HH", strHour);
        dateTime = dateTime.replaceAll("mm", strMinute);
        dateTime = dateTime.replaceAll("ss", "00");

        // back to caller
        if (dateTimeGetter != null)
        {
            dateTimeGetter.onDateTimeGetter(id, dateTime);
        }
    }
}
