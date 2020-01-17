package com.eloisapaz.dataanalysis.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataType {

    private static Logger logger = LoggerFactory.getLogger(DataType.class);

    public static boolean validateDataType(Object[] data) {
        if(!data[0].equals(ID.SALESMAN.numID) && !data[0].equals(ID.CLIENT.numID) && !data[0].equals(ID.SALE.numID)){
            logger.error("Data type is unknown.");
            throw new IllegalArgumentException();
        }else{
            return true;
        }
    }
}
