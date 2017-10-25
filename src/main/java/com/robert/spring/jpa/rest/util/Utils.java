package com.robert.spring.jpa.rest.util;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 24/10/17
 **/
public final class Utils implements java.io.Serializable {

    public static String generateUserName(String name, String lastName, int attempt) {
        final String[] names = name.trim().split(" ");
        final String[] lastNames = lastName.trim().split(" ");
        StringBuilder sbUsername = new StringBuilder();
        for (String sName : names) {
            sbUsername.append(sName).append("_");
        }
        for (String sLastName : lastNames) {
            sbUsername.append(sLastName).append("_");
        }
        return sbUsername.substring(0, sbUsername.length() - attempt).toLowerCase();
    }

    private Utils() {
        throw new AssertionError();
    }

}