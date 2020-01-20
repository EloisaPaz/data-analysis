## Data Analysis Challange

Data analysis is a system able to import lots of flat files, read and
analyse the data, and output a report.

#### Flat file layout

There are 3 kinds of data inside those files. For each kind of data there is a different layout:

- _Salesman_ data has the format ID __001__ and the line will have the following format:
  -  __001çCPFçNameçSalary__
  
- _Customer_ data has the format ID __002__ and the line will have the following format:
  - __002çCNPJçNameçBusinessArea__
  
- _Sales_ data has the format id __003__. Inside the sales row, there is the list of items, which is
  wrapped by square brackets __[]__. The line will have the following format:
   - __003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name__
   
##### The system only read .dat files splitted by a separator, in this case the separator used is "ç" but can be replaced.

The system will read flat files located at:

- _{homepath}/data/in_

After processing, the files will be located at:
- _{homepath}/data/out_

##### How to run the application

Open the terminal inside the directory and type: 

```gradle run```

##### Test coverage:

 ![](https://github.com/EloisaPaz/data-analysis/blob/master/img/coverage.png)
