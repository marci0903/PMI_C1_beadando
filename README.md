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
Beolvas az XML fájlban szereplő számlákat.
```java
  public  static ArrayList<Bill> readBillsFromXml(String filepath){}
```
Kilistázza az XML-ből beolvasott számlákat.
```java
  private static void listBills(ArrayList<Bill> bills){}
```
Új számlákat vesz fel.
```java
  private static void addNewBill(ArrayList<Bill> bills){}
```
Új dokumentum számot olvas be és lekezeli a felhasználó által keletkező beolvasási hibákat.
```java
  private static String scanNewDocument(ArrayList<Bill> bills){}
```
Számlák módosítása és törlése során ügyel a megfelelő dokumentum formát bevitelére.
```java
  private static String modOrDel(){}
```
Ellenőrzi, hogy a felvett dátum valós-e. Megnézi, hogy az év szökőév-e.
```java
 private static int daysInMonth(int month,int year){}
```
Ügyel a megfelelő dátom formátumának bevitelére.
```java
 private static String scanDate()
```
Számlát töröl dokumentum szám alapján.
```java
 private static void deleteBill(ArrayList<Bill> bills){}
```
Számlát módosít dokumentum szám alapján.
```java
  private static void modifyBill(ArrayList<Bill> bills)
```
Létrehozza a táblázat fejrészét.
```java
  private static void tableHeadRow(){}
```
Létrehozza a táblázat sorait.
```java
  private static void tableRow(String documentNumber,String buyer,String date,String fulfillment,
        String deadline,Integer net,Integer VAT,Integer gross,Integer delay){}
```
Elválasztja a táblázat sorait ---------------------- segítségével.
```java
 private static void rowMaker(int hyphenNumber){}
```
Elmenti a számlákat XML fájlba.
```java
 public static void saveUsersToXml(ArrayList<Bill> bills, String filepath){}
```
Lekezeli az egészszámok beolvasása során keletkező hibákat és ügyel hogy 1000-nél nagyobb számokat lehessen megadni.
```java
 public static Integer scanInt(){}
```
Lekezeli delay változó beolvasását.
```java
 public static Integer delay(){}
```
Létrehozza a leszármazott elmet XML mentése során.
```java
 private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {}
```
