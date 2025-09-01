package com.openscm.supplierservice.util;

public class IdGenerator {

    public static String generateSupplierCodeFromId(Long id) {
        return String.format("SUP-%06d", id);
    }

}
