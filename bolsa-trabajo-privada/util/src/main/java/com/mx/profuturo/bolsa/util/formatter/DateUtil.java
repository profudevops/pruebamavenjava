package com.mx.profuturo.bolsa.util.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Julio Arredondo on 08/08/2016.
 */
public class DateUtil {

    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";

    public static String formatServerDate(String serverDate) throws ParseException {
        String viewDate = null;
        DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_FORMAT);
        Date date = formatter.parse(serverDate);
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        viewDate = formatter.format(date);
        return  viewDate;

    }
    
    
    public static String formatFrontComponentDate(String serverDate) throws ParseException {
        String viewDate = null;
        DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_FORMAT);
        Date date = formatter.parse(serverDate);
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        viewDate = formatter.format(date);
        return  viewDate;

    }

    public static long substractCurrentDate(String date, String format) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(format);
        Date minorDate = formatter.parse(date);
        Date currentDate = new Date();
        return currentDate.getTime() - minorDate.getTime();
    }

    public static String formatServerMonthDate(String serverDate) throws ParseException {
        String viewDate = null;
        DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_FORMAT);
        Date date = formatter.parse(serverDate);
        formatter = new SimpleDateFormat("MM/yyyy");
        viewDate = formatter.format(date);
        return  viewDate;
    }

    public static String getYearFromDate(String serverDate) throws ParseException {
        String viewDate = null;
        DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_FORMAT);
        Date date = formatter.parse(serverDate);
        formatter = new SimpleDateFormat("YYYY");
        viewDate = formatter.format(date);
        return  viewDate;
    }

    public static String formatServerDate(String oldDate,String oldFormat){
        String serverDate;
        DateFormat formatter = new SimpleDateFormat(oldFormat);
        Date date = null;
        try {
            date = (Date)formatter.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        serverDate = newFormat.format(date);

        return serverDate;
    }

    public static String formatDateToNewFormat(String oldDate,String oldFormat,String newFormatString){
        String serverDate;
        DateFormat formatter = new SimpleDateFormat(oldFormat);
        Date date = null;
        try {
            date = (Date)formatter.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(newFormatString);
        serverDate = newFormat.format(date);

        return serverDate;
    }

    public static String formatService(String oldDate,String oldFormat){
        String serverDate;
        DateFormat formatter = new SimpleDateFormat(oldFormat);
        Date date = null;
        try {
            date = (Date)formatter.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        serverDate = newFormat.format(date);

        return serverDate;
    }

    public static String getCurrentDate(){
        String serverDate = null;
        Date date = new Date();
        SimpleDateFormat newFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_FORMAT);
        serverDate = newFormat.format(date);
        return serverDate;
    }

    public static String convertDate(String date,String from, String to){
        String serviceDate = null;
        SimpleDateFormat viewDateFormat = new SimpleDateFormat(from, new Locale("es", "MX"));

        try {
            Date viewDate = viewDateFormat.parse(date);
            viewDateFormat.applyPattern(to);
            serviceDate = viewDateFormat.format(viewDate);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return serviceDate;
    }

    public static String addMHS(String endDate) {
        return endDate+" 00:00:00";
    }

    public static String getDateTranslated(String dateString) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        Date newDate = null;
        String newDateTranslated=null;
        try {
            date = (Date)formatter.parse(dateString);

            SimpleDateFormat newFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",new Locale("es", "ES"));
            newDate = date;
            newDateTranslated = newFormat.format(newDate);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return newDateTranslated;
    }
}
