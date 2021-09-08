package com.mx.profuturo.bolsa.security;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luis Miguel Romero on 10/05/2016.
 */
public class ValidatingHttpRequest extends HttpServletRequestWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatingHttpRequest.class);

    private File file;

    private static final String SC_PATTERN = "[a-zA-Z0-9#$?!ñÑáÁéÉíÍóÓúÚ @\\-_.]*";

    private final Pattern pattSC = Pattern.compile(SC_PATTERN);

    public ValidatingHttpRequest(HttpServletRequest request ) {
        super(request);
    }


    @Override
    public String[] getParameterValues(String parameter) {
        LOGGER.debug("getParameterValues method init : " +parameter);
        String[] encodedValues = null;
        String[] values = super.getParameterValues(parameter);
        if (values != null) {
            int count = values.length;
            encodedValues = new String[count];

            for (int i = 0; i < count; i++) {
                encodedValues[i] = stripXSS(values[i]);
            }
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        LOGGER.debug("getParameter method init : " + parameter);
        String value = super.getParameter(parameter);
        return stripXSS(value);

    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }


    private String stripXSS(String value) {
        if (value != null  && !value.isEmpty()) {
            try {
                //Patrón de caracteres especiales
                Matcher m = pattSC.matcher(value.toLowerCase());
                if (!m.matches()) {
                    //Añadir los reemplazos que sean necesarios.
                    value = value.replaceAll("'", "");
                    value = value.replaceAll("\"", "");
                }
                //Patrones XSS
                for(Pattern scriptPattern : XSSCheckPatternConstants.getPatterns()){
                    m = scriptPattern.matcher(value.toLowerCase());
                    if (m.matches()) {
                        AntiSamy as = new AntiSamy();
                        CleanResults cr = as.scan(value, this.file);
                        value = cr.getCleanHTML();
                    }
                }
            } catch (ScanException e) {
                LOGGER.error("Error cleaning HTML code from request", e);
            } catch (PolicyException e) {
                LOGGER.error("Error cleaning HTML code from request", e);
            }
        }
        return value;
    }

    public void setFile(File antisamyFile){
        this.file =antisamyFile;
    }
}


