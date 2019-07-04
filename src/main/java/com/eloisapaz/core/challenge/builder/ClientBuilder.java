package com.eloisapaz.core.challenge.builder;

import com.eloisapaz.core.challenge.model.Bridge;
import com.eloisapaz.core.challenge.model.Client;

public class ClientBuilder implements Builder{

    private static final int NAME = 1;
    private static final int CNPJ = 2;
    private static final int PAYMENT = 3;

    @Override
    public Bridge build(String[] data) {
        return new Client(
                data[NAME],
                data[CNPJ],
                data[PAYMENT]
        );
    }
}
