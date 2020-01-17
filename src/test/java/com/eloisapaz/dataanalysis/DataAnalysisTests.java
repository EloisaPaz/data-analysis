package com.eloisapaz.dataanalysis;

import com.eloisapaz.dataanalysis.builder.ClientBuilder;
import com.eloisapaz.dataanalysis.builder.SaleBuilder;
import com.eloisapaz.dataanalysis.builder.SalesmanBuilder;
import com.eloisapaz.dataanalysis.domain.DataType;
import com.eloisapaz.dataanalysis.model.Client;
import com.eloisapaz.dataanalysis.model.Item;
import com.eloisapaz.dataanalysis.model.Sale;
import com.eloisapaz.dataanalysis.model.Salesman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataAnalysisTests {

	private String salesman;
	private String client;
	private String sale;

	@Before
	public void startUp() {
		String [] data = {"001ç03180308029çEloisaç12300", "002ç33575451000106çJanainaçVendas", "003ç27ç[1-50-20.0,2-2-5.50,3-5-10.5]çDaniela"};
	}

	@Test
	public void testSale() {
		String line = "003ç27ç[1-1-20.0,2-2-5.50,3-1-10.5]çDaniela";
		String separator = String.valueOf(line.charAt(3));
		String [] splittedLine = line.split(separator);
		SaleBuilder saleBuilder = new SaleBuilder();
		Sale sale = saleBuilder.build(splittedLine);
		assertEquals("27", sale.getIdSale());
		assertEquals("Daniela", sale.getSalesman().getName());
	}

	@Test
	public void testSalesman() {
		String line = "001ç03180308029çEloisaç12300";
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
		String line = "002ç33575451000106çJanainaçVendas";
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
