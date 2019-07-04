package com.eloisapaz.core.challenge.builder;

import com.eloisapaz.core.challenge.model.Bridge;
import com.eloisapaz.core.challenge.model.Salesman;

public class SalesmanBuilder implements Builder{

    private static final int NAME = 2;
    private static final int CPF = 1;
    private static final int PAYMENT = 3;

    @Override
    public Bridge build(String[] data) {
        return new Salesman(
                data[NAME],
                data[CPF],
                Double.parseDouble(data[PAYMENT])
        );
    }
}