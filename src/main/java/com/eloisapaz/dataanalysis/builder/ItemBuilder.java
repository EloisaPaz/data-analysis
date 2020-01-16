package com.eloisapaz.dataanalysis.builder;

import com.eloisapaz.dataanalysis.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder {

    private static final int IDITEM = 0;
    private static final int QUANTIDADE = 1;
    private static final int VALOR = 2;

    public Item build(String[] dados) {
        return new Item(dados[IDITEM],
                Integer.parseInt(dados[QUANTIDADE]),
                Double.parseDouble(dados[VALOR]));
    }
}
