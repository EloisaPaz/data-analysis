package com.eloisapaz.core.challenge.file;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import com.eloisapaz.core.challenge.builder.SaleBuilder;
import com.eloisapaz.core.challenge.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eloisapaz.core.challenge.builder.ClientBuilder;
import com.eloisapaz.core.challenge.builder.SalesmanBuilder;
import com.eloisapaz.core.challenge.model.ID;
import com.eloisapaz.core.challenge.model.Sale;
import com.eloisapaz.core.challenge.model.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FileProcessor {

    protected LinkedHashSet<Salesman> salesmenList = new LinkedHashSet<>();
    protected LinkedHashSet<Sale> sales = new LinkedHashSet<>();
    protected LinkedHashSet<Client> clients = new LinkedHashSet<>();
    private ApplicationContext applicationContext;
    private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);

    @Autowired
    public FileProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

    public void readFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String character = String.valueOf(line.charAt(3));
            String [] splitLine = line.split(character);

            if(splitLine.length > 0) {
                if(splitLine[0].equals(ID.SALESMAN.numID)) {
                    SalesmanBuilder salesmanBuilder = (SalesmanBuilder) applicationContext.getBean("salesmanBuilder");
                    Salesman salesman = (Salesman) salesmanBuilder.build(splitLine);
                    this.salesmenList.add(salesman);
                }

                if(splitLine[0].equals(ID.CLIENT.numID)) {
                    ClientBuilder clientBuilder = (ClientBuilder) applicationContext.getBean("clientBuilder");
                    Client client = (Client) clientBuilder.build(splitLine);
                    this.clients.add(client);
                }

                if(splitLine[0].equals(ID.SALE.numID)) {
                    SaleBuilder saleBuilder = (SaleBuilder) applicationContext.getBean("saleBuilder", this.salesmenList);
                    Sale sale = (Sale) saleBuilder.build(splitLine);
                    this.sales.add(sale);
                }
            }
        }
        scan.close();
    }

    public void getReport(String reportPathName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(reportPathName + "report" + ".done.dat"))) {
            out.write("Total of clients is:: " + clients.size());
            out.newLine();
            out.write("Total sellers is:: " + salesmenList.size());
            out.newLine();
            out.write("The biggest sale is: " + this.getBiggestSale());
            out.newLine();
            out.write("The worst seller is: " + this.getWorstSeller());
        } catch (Exception e) {
            logger.error("Unable to generate report!", e);
        }
    }

    private String getBiggestSale() {
        double biggestSalePrice = 0;
        String idBiggestSale = "";
        for(Sale sale : this.sales) {
            if(sale.getTotal() > biggestSalePrice) {
                biggestSalePrice = sale.getTotal();
                idBiggestSale = sale.getIdSale();
            }
        }
        return idBiggestSale;
    }

    private String getWorstSeller() {
        Salesman worstSeller = this.salesmenList.iterator().next();
        for(Salesman salesman : this.salesmenList) {
            if(salesman.getTotalSales() < worstSeller.getTotalSales()) {
                worstSeller = salesman;
            }
        }
        return worstSeller.getName();
    }
}