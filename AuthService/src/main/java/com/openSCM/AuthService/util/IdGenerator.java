package com.openscm.authservice.util;

public class IdGenerator {

    public static String generateUserId(String userType){
        String prefix = "USR";
        if (userType != null) {
            prefix = switch (userType.toUpperCase()) {
                case "CUSTOMER" -> "CUST";
                case "SUPPLIER" -> "SUPP";
                case "ADMIN" -> "ADM";
                default -> prefix;
            };
        }

        String timestampPart = String.valueOf(System.currentTimeMillis());
        timestampPart = timestampPart.substring(timestampPart.length() - 5);
        int randomNum = (int) (Math.random() * 900) + 100;

        return prefix + "-" + timestampPart + randomNum;
    }
}
