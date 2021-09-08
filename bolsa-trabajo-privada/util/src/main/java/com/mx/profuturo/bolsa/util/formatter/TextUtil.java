package com.mx.profuturo.bolsa.util.formatter;

public class TextUtil {

    public static String capitalize(String source){
        String response = null;
        String original = null;
        if(source.length()==0){
            response = source;
        }else{
            original = source.toLowerCase();
            response = original.substring(0, 1).toUpperCase() + original.substring(1);
        }
        return response;
    }
    public static String replaceEmpty(String source){
        return replaceEmpty(source,false);
    }

    public static String replaceEmpty(String source, Boolean checkZero){
        String response = null;
        if(checkZero){
            response =  (null != source && !("".equals(source))&&!(("0".equals(source))))? source:"N/A";
        }else{
            response =  (null != source && !("".equals(source)))? source:"N/A";
        }
        return response;
    }

    public static String replaceNull(String source) {
        return(source == null)?"":source;
    }
}



