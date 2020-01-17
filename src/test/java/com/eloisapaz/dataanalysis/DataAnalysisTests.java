package com.eloisapaz.dataanalysis;

import com.eloisapaz.dataanalysis.builder.ClientBuilder;
import com.eloisapaz.dataanalysis.builder.SaleBuilder;
import com.eloisapaz.dataanalysis.builder.SalesmanBuilder;
import com.eloisapaz.dataanalysis.domain.DataType;
import com.eloisapaz.dataanalysis.file.FileProcessor;
import com.eloisapaz.dataanalysis.model.Client;
import com.eloisapaz.dataanalysis.model.Sale;
import com.eloisapaz.dataanalysis.model.Salesman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataAnalysisTests {

	private String [] data;

	@Before
	public void startUp() {
		data = new String[]{"001ç03180308029çEloisaç12300", "002ç33575451000106çJanainaçVendas", "003ç27ç[1-50-20.0,2-2-5.50,3-1-10.5]çDaniela", "003ç28ç[1-2-20.0,2-2-5.50,3-1-10.5]çEloisa"};
	}

	@Test
	public void testSale() {
		String line = data[2];
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		SaleBuilder saleBuilder = new SaleBuilder();
		Sale sale = saleBuilder.build(splittedLine);
		assertEquals("27", sale.getIdSale());
	}

	@Test
	public void testWorstSeller() {
		FileProcessor fileProcessor = new FileProcessor();
		String line = data[2];
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		SaleBuilder saleBuilder = new SaleBuilder();
		Sale sale = saleBuilder.build(splittedLine);

		String line2 = data[3];
		String separator2 = String.valueOf(line.charAt(3));
		String [] splittedLine2 = line.split(separator);
		SaleBuilder saleBuilder2 = new SaleBuilder();
		Sale sale2 = saleBuilder.build(splittedLine);

		assertEquals("Eloisa", fileProcessor.getWorstSeller());
	}

	@Test
	public void testBiggestSale() {
		FileProcessor fileProcessor = new FileProcessor();
		String line = data[2];
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		SaleBuilder saleBuilder = new SaleBuilder();
		Sale sale = saleBuilder.build(splittedLine);

		assertEquals("27", fileProcessor.getBiggestSale());
	}

	@Test
	public void testSalesman() {
		String line = data[0];
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		SalesmanBuilder salesmanBuilder = new SalesmanBuilder();
		Salesman salesman = salesmanBuilder.build(splittedLine);
		assertEquals("Eloisa", salesman.getName());
		assertEquals("03180308029", salesman.getCpf());
		assertEquals("12300.0", salesman.getPayment().toString());
	}

	@Test
	public void testClient() {
		String line = data[1];
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		ClientBuilder clientBuilder = new ClientBuilder();
		Client client = clientBuilder.build(splittedLine);
		assertEquals("33575451000106", client.getCnpj());
		assertEquals("Janaina", client.getName());
		assertEquals("Vendas", client.getBusinessArea());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDataTypeUnknown() {
		String line = "005ç03180308029çEloisaç12300";
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		DataType.validateDataType(splittedLine);
	}
}
