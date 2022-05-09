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
Beolvas az XML fájlban szereplő számlákat.
```java
  private static void listBills(ArrayList<Bill> bills){}
```
Kilistázza az XML-ből beolvasott számlákat.
```java
  private static void addNewBill(ArrayList<Bill> bills){}
```
Új számlákat vesz fel.
```java
  private static String scanNewDocument(ArrayList<Bill> bills){}
```
Új dokumentum számot olvas be és lekezeli a felhasználó által keletkező beolvasási hibákat.
