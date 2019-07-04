package com.eloisapaz.core.challenge.config;

import com.eloisapaz.core.challenge.builder.ClientBuilder;
import com.eloisapaz.core.challenge.builder.SaleBuilder;
import com.eloisapaz.core.challenge.builder.SalesmanBuilder;
import com.eloisapaz.core.challenge.file.FileProcessor;
import com.eloisapaz.core.challenge.file.FileConfig;
import com.eloisapaz.core.challenge.model.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.eloisapaz.core.challenge.model.Item;
import com.eloisapaz.core.challenge.model.Sale;
import com.eloisapaz.core.challenge.model.Salesman;
import java.util.LinkedHashSet;

@Configuration
@ComponentScan(basePackages = "com.eloisapaz.core.challenge")

public class DataAnalysisConfig {

    @Bean
    @Scope("prototype")
    public Client client(String name, String cnpj, String businessArea) {
        return new Client(name, cnpj, businessArea);
    }

    @Bean
    @Scope("prototype")
    public Item item(String idItem, Integer amount, Double price){
        return new Item(idItem, amount, price);
    }

    @Bean
    @Scope("prototype")
    public Sale sale(String idSale, LinkedHashSet<Item> itens){
        return new Sale(idSale, itens);
    }

    @Bean
    @Scope("prototype")
    public Salesman salesman(String name, String cpf, Double payment){
        return new Salesman(name, cpf, payment);
    }

    @Bean
    @Scope("prototype")
    public SalesmanBuilder salesmanBuilder(){
        return new SalesmanBuilder();
    }

    @Bean
    @Scope("prototype")
    public ClientBuilder clientBuilder(){
        return new ClientBuilder();
    }

    @Bean
    @Scope("prototype")
    public SaleBuilder saleBuilder(LinkedHashSet<Salesman> salesman){
        return new SaleBuilder(salesman);
    }

    @Bean
	public FileProcessor fileProcessor(ApplicationContext applicationContext) {
		return new FileProcessor(applicationContext);
    }
    
    @Bean
	public FileConfig fileConfig() {
		return new FileConfig();
	}
}
