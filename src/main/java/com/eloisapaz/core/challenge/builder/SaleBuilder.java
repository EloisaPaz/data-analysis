package com.eloisapaz.core.challenge.builder;

import java.util.LinkedHashSet;
import com.eloisapaz.core.challenge.model.Item;
import com.eloisapaz.core.challenge.model.Sale;
import com.eloisapaz.core.challenge.model.Bridge;
import com.eloisapaz.core.challenge.model.Salesman;

public class SaleBuilder implements Builder{

    private static final int IDSALE = 1;
    private static final int SALESMAN = 3;
    private static final int ITENS = 2;

    private static LinkedHashSet<Salesman> salesmanSale;

    public SaleBuilder(LinkedHashSet<Salesman> salesman) {
        salesmanSale = salesman;
    }

    @Override
    public Bridge build(String[] data) {
        Sale sale = new Sale(data[IDSALE],
                this.getItensSale(data));

        sale.setTotal(sale.getItens().stream().mapToDouble(item->item.getTotal()).sum());
        salesmanSale.forEach(salesmanSale -> {
            if(salesmanSale.getName().equalsIgnoreCase(data[SALESMAN])) {
                salesmanSale.setTotalSales(sale.getTotal());
                sale.setSalesman(salesmanSale);
            }
        });
        return sale;
    }

    private LinkedHashSet<Item> getItensSale(String[] data){
        LinkedHashSet<Item> itensSale = new LinkedHashSet<>();
        ItemBuilder itemBuilder = new ItemBuilder();
        String [] itens = data[ITENS].replace("[", "").replace("]", "").split(",");
        for (String item : itens) {
            String[] splitItens = item.split("-");
            itensSale.add(itemBuilder.build(splitItens));
        }
        return itensSale;
    }
}
