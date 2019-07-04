package com.eloisapaz.core.challenge.model;

import java.util.LinkedHashSet;

public class Sale implements Bridge{

    private String idSale;
    private Salesman salesman;
    private LinkedHashSet<Item> itens;
    private Double total;

    public Sale(String idSale, LinkedHashSet<Item> itens) {
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

    public LinkedHashSet<Item> getItens() {
        return itens;
    }

    public void setItens(LinkedHashSet<Item> itens) {
        this.itens = itens;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}