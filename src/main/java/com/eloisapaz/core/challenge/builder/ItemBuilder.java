package com.eloisapaz.core.challenge.builder;

import com.eloisapaz.core.challenge.model.Item;

public class ItemBuilder implements Builder{

    private static final int IDITEM = 0;
    private static final int QUANTIDADE = 1;
    private static final int VALOR = 2;

    @Override
    public Item build(String[] dados) {
        return new Item(dados[IDITEM],
                Integer.parseInt(dados[QUANTIDADE]),
                Double.parseDouble(dados[VALOR]));
    }
}

