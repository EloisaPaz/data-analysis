package com.eloisapaz.dataanalysis.model;

public class Client {

    private String name;
    private String cnpj;
    private String businessArea;

    public Client(String name, String cpf, String businessArea) {
        this.name = name;
        this.cnpj = cpf;
        this.businessArea = businessArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}
