package com.mx.profuturo.bolsa.util.formatter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alejandro.hernandez on 15/11/2016.
 */
public class MonthSorter {

    public Map<Integer,String> mapMonths(int months){

        Locale locale = new Locale("es", "MX");
        Calendar cal = GregorianCalendar.getInstance(locale);
        SimpleDateFormat fmt = new SimpleDateFormat("MMMM",locale);
        fmt.setCalendar(cal);
        Map<Integer,String>  monthsSorted = new HashMap<Integer,String>();
        int j = 1;
        for(int i = 0; i < months; i++)
        {
            cal.add(GregorianCalendar.MONTH,-1);
            String dateFormatted2 = capitalize(fmt.format(cal.getTime()));
            monthsSorted.put(j,dateFormatted2);
            j++;
        }
        return monthsSorted;

    }

    public  String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }


}
