# Refactoring Java

The code creates an information slip about movie rentals for specified customer.


## To run the tests:

```
mvn test
```

## To get rental info for customer(s):

```
mvn compile exec:java -D"exec.mainClass"="com.etraveli.Main" -D"exec.args"="{customerIds}"
```
### where `{customerId}` is space separated list of customer ids. Currently, there are 5 preset customers: *C001, C002, C003, C004, C005