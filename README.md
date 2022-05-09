Számla nyilvántartó program
===
A program segítségével a felhasználónak lehetősége van számlák tárólására, megjelenítésére, hibás adatok esetén módosításra és szükség esetén a számla törlésére.

## Osztály példányváltozói
```java
    private String documentNumber;
    private String buyer;
    private String date;
    private String fulfillment;
    private String deadline;
    private int net;
    private int VAT;
    private int gross;
    private int delay;
```
## Metódusok
```java
  public  static ArrayList<Bill> readBillsFromXml(String filepath){}
```
Beolvas az XML fájlban szerepló számlákat.
