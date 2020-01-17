package com.eloisapaz.dataanalysis.file;

import com.eloisapaz.dataanalysis.builder.ClientBuilder;
import com.eloisapaz.dataanalysis.builder.SaleBuilder;
import com.eloisapaz.dataanalysis.builder.SalesmanBuilder;
import com.eloisapaz.dataanalysis.model.Client;
import com.eloisapaz.dataanalysis.domain.ID;
import com.eloisapaz.dataanalysis.model.Sale;
import com.eloisapaz.dataanalysis.model.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import static com.eloisapaz.dataanalysis.domain.DataType.validateDataType;

@Component
public class FileProcessor {

    private List<Salesman> salesmanList = new ArrayList<>();
    private LinkedHashSet<Sale> sales = new LinkedHashSet<>();
    private LinkedHashSet<Client> clients = new LinkedHashSet<>();
    private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);

    public void readFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String separator = String.valueOf(line.charAt(3));
            String [] splittedLine = line.split(separator);

            if(splittedLine.length > 0 && validateDataType(splittedLine)) {

                if(splittedLine[0].equals(ID.SALESMAN.numID)) {
                    SalesmanBuilder salesmanBuilder = new SalesmanBuilder();
                    Salesman salesman = salesmanBuilder.build(splittedLine);
                    salesmanList.add(salesman);
                }

                if(splittedLine[0].equals(ID.CLIENT.numID)) {
                    ClientBuilder clientBuilder = new ClientBuilder();
                    Client client = clientBuilder.build(splittedLine);
                    clients.add(client);
                }

                if(splittedLine[0].equals(ID.SALE.numID)) {
                    SaleBuilder saleBuilder = new SaleBuilder(salesmanList);
                    Sale sale = saleBuilder.build(splittedLine);
                    sales.add(sale);
                }
            }
        }
        scan.close();
    }

    public void getReport(String reportPathName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(reportPathName + "report" + ".done.dat"))) {
            out.write("Total of clients is: " + clients.size());
            out.newLine();
            out.write("Total sellers is: " + salesmanList.size());
            out.newLine();
            out.write("The biggest sale is: " + this.getBiggestSale());
            out.newLine();
            out.write("The worst seller is: " + this.getWorstSeller());
        } catch (Exception e) {
            logger.error("Unable to generate report!", e);
        }
    }

    public String getBiggestSale() {
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

    public String getWorstSeller() {
        Salesman worstSeller = this.salesmanList.iterator().next();
        for(Salesman salesman : this.salesmanList) {
            if(salesman.getTotalSales() < worstSeller.getTotalSales()) {
                worstSeller = salesman;
            }
        }
        return worstSeller.getName();
    }

}
