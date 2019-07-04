## Data Analysis Challange

Data analysis system able to import lots of flat files, read and
analyse the data, and output a report.

#### Flat file layout

There are 3 kinds of data inside those files. For each kind of data there is a different layout:

- Salesman data has the format ID 001 and the line will have the following format:
  - 001çCPFçNameçSalary 
  
- Customer data has the format ID 002 and the line will have the following format:
  - 002çCNPJçNameçBusinessArea
  
- Sales data has the format id 003. Inside the sales row, there is the list of items, which is
  wrapped by square brackets []. The line will have the following format:
   - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
   
##### The system only read .dat files splitted by a separator, in this case the separator used is "ç" but can be replaced.

The system will read flat files located at: {homepath}/data/in

After processing, the files will be located at: {homepath}/data/out

##### How to run the application

Open the terminal inside the directory and type: 
```gradle run```
