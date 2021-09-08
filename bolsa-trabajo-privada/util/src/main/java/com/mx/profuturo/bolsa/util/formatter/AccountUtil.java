package com.mx.profuturo.bolsa.util.formatter;

public class AccountUtil {
    private AccountUtil(){
    }

    public static String doMask(String account){
        String response = "";
        if(null != account){
            int size =account.length()-4;
            size--;
            String sub = account.substring(size,account.length());
            response = String.format("%10s", sub).replace(' ', '*');
        }
        return response;
    }
}
