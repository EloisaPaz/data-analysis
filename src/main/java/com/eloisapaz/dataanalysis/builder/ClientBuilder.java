package com.eloisapaz.dataanalysis.builder;

import com.eloisapaz.dataanalysis.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientBuilder {

    private static final int CNPJ = 2;
    private static final int NAME = 1;
    private static final int PAYMENT = 3;

    public Client build(String[] data) {
        return new Client(
                data[CNPJ],
                data[NAME],
                data[PAYMENT]
        );
    }
}
