package com.eloisapaz.dataanalysis.model;

public class Item {

    private String idItem;
    private Integer amount;
    private Double price;
    private Double total;

    public Item(String idItem, Integer amount, Double price) {
        this.idItem = idItem;
        this.amount = amount;
        this.price = price;
        this.total = price * amount;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
