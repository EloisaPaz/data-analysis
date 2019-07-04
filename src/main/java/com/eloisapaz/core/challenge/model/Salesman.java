package com.eloisapaz.core.challenge.model;

public class Salesman implements Bridge{

    private String name;
    private String cpf;
    private Double payment;
    private Double totalSales;

    public Salesman(String name, String cpf, Double payment) {
        this.name = name;
        this.cpf = cpf;
        this.payment = payment;
        this.totalSales = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales += totalSales;
    }
}