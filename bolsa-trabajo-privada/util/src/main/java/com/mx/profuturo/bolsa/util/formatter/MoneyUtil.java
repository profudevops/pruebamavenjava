package com.mx.profuturo.bolsa.util.formatter;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Julio Arredondo on 21/07/2016.
 */
public class MoneyUtil {

    public static String format(float amount){
        Locale locMEX = new Locale("es", "MX");
        return DecimalFormat.getCurrencyInstance(locMEX).format(amount);
    }

    public static String format(String amount){
        Locale locMEX = new Locale("es", "MX");
        return DecimalFormat.getCurrencyInstance(locMEX).format(amount);
    }

    public static String dollarCent(String amount){
        return new StringBuilder("$").append(amount).append(".00").toString();
    }



    public static int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }
}
