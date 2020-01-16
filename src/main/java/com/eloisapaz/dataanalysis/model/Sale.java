package com.eloisapaz.dataanalysis.model;

import java.util.List;

public class Sale {

    private String idSale;
    private Salesman salesman;
    private List<Item> itens;
    private Double total;

    public Sale(String idSale, List<Item> itens) {
        this.idSale = idSale;
        this.itens = itens;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
