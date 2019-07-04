package com.eloisapaz.core.challenge.model;

public enum ID {

    SALESMAN("001"),
    CLIENT("002"),
    SALE("003");

    public String numID;
    private ID(String numID) {
        this.numID = numID;
    }
}