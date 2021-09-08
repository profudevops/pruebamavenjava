package com.mx.profuturo.bolsa.util.encrypter;

import org.springframework.security.crypto.codec.Base64;

import java.nio.charset.Charset;

/**
 * Created by Julio Arredondo on 27/10/2016.
 */
public class Base64Util {

    public static final String UTF_8 = "UTF-8";

    private Base64Util(){
        /*default constructor*/
    }

    public static String base64Encode(String token) {
        byte[] encodedBytes = Base64.encode(token.getBytes());
        return new String(encodedBytes, Charset.forName(UTF_8));
    }


    public static String base64Decode(String token) {
        byte[] decodedBytes = Base64.decode(token.getBytes());
        return new String(decodedBytes, Charset.forName(UTF_8));
    }

    public static String base64Encode(byte[] bytes){
        byte[] encodedBytes = Base64.encode(bytes);
        return new String(encodedBytes, Charset.forName(UTF_8));
    }


    public static String base64Decode(byte[] bytes) {
        byte[] decodedBytes = Base64.decode(bytes);
        return new String(decodedBytes, Charset.forName(UTF_8));
    }

    public static boolean isStringBase64(String value){
        return Base64.isBase64(value.getBytes());
    }


}
