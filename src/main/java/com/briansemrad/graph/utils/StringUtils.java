package com.briansemrad.graph.utils;

/**
 * A collection of useful String utils that the JDK 1.5 doesn't provide.
 * @author Brian Semrad
 * @version 1.0
 */
public abstract class StringUtils {

    private static final String EMPTY_STRING = "";

    /**
     * Checks to see if the string provided is null, length of zero, or contains nothing but blank spaces 
     * @param checkString
     * @return 
     */
    public static boolean isNullOrEmpty(String checkString) {
        return (checkString == null || checkString.length() == 0 || "".equals(checkString.trim()));
    }

    /**
     * Gets a predefined empty string
     * @return 
     */
    public static String empty() {
        return EMPTY_STRING;
    }
}
