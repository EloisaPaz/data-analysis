package com.eloisapaz.dataanalysis.builder;

import com.eloisapaz.dataanalysis.model.Item;
import com.eloisapaz.dataanalysis.model.Sale;
import com.eloisapaz.dataanalysis.model.Salesman;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaleBuilder {

    private static final int IDSALE = 1;
    private static final int SALESMAN = 3;
    private static final int ITENS = 2;

    private static List<Salesman> salesmanSale;

    public SaleBuilder(List<Salesman> salesman) {
        salesmanSale = salesman;
    }

    public SaleBuilder() {
    }

    public Sale build(String[] data) {
        Sale sale = new Sale(data[IDSALE],
                this.getItensSale(data));

        sale.setTotal(sale.getItens().stream().mapToDouble(Item::getTotal).sum());

        salesmanSale.forEach(salesmanSale -> {
            if(salesmanSale.getName().equalsIgnoreCase(data[SALESMAN])) {
                salesmanSale.setTotalSales(sale.getTotal());
                sale.setSalesman(salesmanSale);
            }
        });
        return sale;
    }

    private List<Item> getItensSale(String[] data){
        List<Item> itensSale = new ArrayList<>();
        ItemBuilder itemBuilder = new ItemBuilder();
        String [] itens = data[ITENS].replace("[", "").replace("]", "").split(",");
        for (String item : itens) {
            String[] splitItens = item.split("-");
            itensSale.add(itemBuilder.build(splitItens));
        }
        return itensSale;
    }
}
