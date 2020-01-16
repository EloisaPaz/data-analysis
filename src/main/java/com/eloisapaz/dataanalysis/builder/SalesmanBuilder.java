package com.eloisapaz.dataanalysis.builder;

import com.eloisapaz.dataanalysis.model.Salesman;
import org.springframework.stereotype.Component;

@Component
public class SalesmanBuilder {

    private static final int NAME = 2;
    private static final int CPF = 1;
    private static final int PAYMENT = 3;

    public Salesman build(String[] data) {
        return new Salesman(
                data[NAME],
                data[CPF],
                Double.parseDouble(data[PAYMENT])
        );
    }
}
