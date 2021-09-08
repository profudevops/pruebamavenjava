package com.mx.profuturo.bolsa.security;

import java.util.regex.Pattern;

public class XSSCheckPatternConstants {

    //General pattern
    private static final String GENERAL_PATTERN = "(.*\\<.*)|(.*\\>.*)|(.*\\'.*)|(.*(?=%3c).*)|(.*(?=%3e).*)|(.*(?=&lt;).*)|(.*(?=&gt;).*)|(.*(?=%27;).*)";

    // Avoid anything between script tags
    private static final String BETWEEN_SCRIPT_A_PATTERN = "<script>(.*?)</script>";

    // Avoid anything in a src='...' type of expression
    private static final String SRC_A_PATTERN = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
    private static final String SRC_B_PATTERN = "src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";

    // Remove any lonesome </script> tag
    private static final String LONESOME_SCRIPT_A_PATTERN = "</script>";
    private static final String LONESOME_SCRIPT_B_PATTERN = "<script(.*?)>";

    // Avoid eval(...) expressions
    private static final String EVAL_A_PATTERN = "eval\\((.*?)\\)";

    // Avoid expression(...) expressions
    private static final String EXPRESSION_A_PATTERN = "expression\\((.*?)\\)";

    // Avoid javascript:... expressions
    private static final String JAVASCRIPT_A_PATTERN = "javascript:";

    // Avoid vbscript:... expressions
    private static final String VBSCRIPT_A_PATTERN = "vbscript:";

    // Avoid onload= expressions
    private static final String ONLOAD_A_PATTERN = "onload(.*?)=";

    private static final Pattern[] PATTERNS = new Pattern[]{
            Pattern.compile(GENERAL_PATTERN),
            Pattern.compile(BETWEEN_SCRIPT_A_PATTERN, Pattern.CASE_INSENSITIVE),
            Pattern.compile(SRC_A_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(SRC_B_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(LONESOME_SCRIPT_A_PATTERN, Pattern.CASE_INSENSITIVE),
            Pattern.compile(LONESOME_SCRIPT_B_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(EVAL_A_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(EXPRESSION_A_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(JAVASCRIPT_A_PATTERN, Pattern.CASE_INSENSITIVE),
            Pattern.compile(VBSCRIPT_A_PATTERN, Pattern.CASE_INSENSITIVE),
            Pattern.compile(ONLOAD_A_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    private XSSCheckPatternConstants() {
        /**Default Constructor*/
    }

    public static Pattern[] getPatterns(){
        return PATTERNS;
    }
}
