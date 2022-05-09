package manager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.sql.rowset.spi.XmlReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ManagerMain {
    private static  final  Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
         ArrayList<Bill> bills=readBillsFromXml("src/main/resources/bills.xml");
         int choice = -1;
         while (choice != 0) {
             switch (choice) {
                 case 1 -> listBills(bills);
                 case 2 -> addNewBill(bills);
                 case 3 -> modifyBill(bills);
                 case 4 -> deleteBill(bills);
             }
             System.out.println("1 - List bills\r\n2 - Add new bill\r\n"
                     + "3 - Modify a bill\r\n4 - Delete a bill\r\n0 - Exit");
             try {
                 choice = scanner.nextInt();
                 scanner.nextLine();
                 if (choice < 0 || choice > 4) {
                     System.out.println("Not valid option.");
                 }
             } catch (InputMismatchException e) {
                 System.out.println("Not valid option.");
                 scanner.nextLine();
             }
         }
         saveUsersToXml(bills,"src/main/resources/bills.xml");
    }
    public  static ArrayList<Bill> readBillsFromXml(String filepath){
        ArrayList<Bill> bills=new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(filepath);
            Element rootElement=document.getDocumentElement();
            NodeList childNodeList=rootElement.getChildNodes();
            Node node;
            for (int i=0; i<childNodeList.getLength(); i++){
                node=childNodeList.item(i);
                if (childNodeList.item(i).getNodeType()==Node.ELEMENT_NODE){

                    NodeList childNodeOfUserTag=node.getChildNodes();
                    String documentNumber="",buyer="",date="",fulfillment="",deadline="",net="",VAT="",gross="",delay="";
                    for (int j=0; j<childNodeOfUserTag.getLength(); j++){
                        if (childNodeOfUserTag.item(j).getNodeType()==Node.ELEMENT_NODE){
                            if(childNodeOfUserTag.item(j).getNodeName().equals("documentNumber")){
                                documentNumber=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("buyer")){
                                buyer=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("date")){
                                date=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("fulfillment")){
                                fulfillment=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("deadline")){
                                deadline=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("net")){
                                net=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("VAT")){
                                VAT=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("gross")){
                                gross=childNodeOfUserTag.item(j).getTextContent();
                            }
                            else if(childNodeOfUserTag.item(j).getNodeName().equals("delay")){
                                delay=childNodeOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    bills.add(new Bill(documentNumber,buyer,date,fulfillment,deadline,Integer.parseInt(net),Integer.parseInt(VAT),Integer.parseInt(gross),Integer.parseInt(delay)));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return bills;
    }
    private static void listBills(ArrayList<Bill> bills){
        tableHeadRow();
        for(Bill bill:bills){
            tableRow(bill.getDocumentNumber(),bill.getBuyer(),bill.getDate(),bill.getFulfillment(),bill.getDeadline(),
            bill.getNet(),bill.getVAT(),bill.getGross(),bill.getDelay());
        }
        System.out.print("+");
        row();
        System.out.println();
    }
    private static void addNewBill(ArrayList<Bill> bills){
        System.out.println("Enter  document number of new bill.");
        String newDocumentNumber=scanNewDocument(bills);
        System.out.println("Enter name of the buyer.");
        String newBuyer=scanner.nextLine();
        System.out.println("Enter date of new bill.(2022-01-01)");
        String newDate=scanDate();
        System.out.println("Enter fulfillment of new bill.(2022-01-01)");
        String newFulfillment=scanDate();
        System.out.println("Enter deadline of new bill.(2022-01-01)");
        String newDeadline=scanDate();
        System.out.println("Enter gross of new bill.");
        Integer newGross=scanInt();
        scanner.nextLine();
        System.out.println("Enter delay of new bill.");
        Integer newDelay=scanInt();
        scanner.nextLine();
        int newVAT= (int) (newGross*0.27);
        int newNet=(int) (newGross*0.73);
        bills.add(new Bill(newDocumentNumber,newBuyer,newDate,newFulfillment,newDeadline,newNet,newVAT,newGross,newDelay));
    }
    private static String scanNewDocument(ArrayList<Bill> bills){
        while (true) {
            try {
                String newDocument = scanner.nextLine();
                String[] splitDocument = newDocument.split("/");
                if (Integer.parseInt(splitDocument[0]) <= 0) {
                    System.out.println("Make sure the format is correct. (2000/2022)");
                } else {
                    if (Integer.parseInt(splitDocument[1]) < 2000 && Integer.parseInt(splitDocument[1]) >= 2022) {
                        System.out.println("Make sure the date is relevant.(Between 2000 and 2022)");
                    } else {
                        boolean isIn=false;
                        for(Bill bill:bills){
                            if(bill.getDocumentNumber().equals(newDocument)){
                                isIn=true;
                            }
                        }
                        if(isIn){
                            System.out.println("Make sure the document number is unique.");
                        }else{
                            return newDocument;
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Make sure the format is correct. (2000/2022)");
            }
        }
    }
    private static String modOrDel(){
        while (true) {
            try {
                String newDocument = scanner.nextLine();
                String[] splitDocument = newDocument.split("/");
                if (Integer.parseInt(splitDocument[0]) <= 0) {
                    System.out.println("Make sure the format is correct. (2000/2022)");
                } else {
                    if (Integer.parseInt(splitDocument[1]) < 2000 && Integer.parseInt(splitDocument[1]) >= 2022) {
                        System.out.println("Make sure the date is relevant.(Between 2000 and 2022)");
                    } else {

                            return newDocument;


                    }
                }
            } catch (Exception e) {
                System.out.println("Make sure the format is correct. (2000/2022)");
            }
        }
    }
    private static int daysInMonth(int month,int year){
         if(!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)){
            switch (month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:return 31;
                case 2: return 28;
                case 4: case 6: case 9: case 11: return 30;
            }
        }else{
             switch (month) {
             case 1: case 3: case 5: case 7: case 8: case 10: case 12:return 31;
             case 2: return 29;
             case 4: case 6: case 9: case 11: return 30;
             }
         }

        return -1;
    }
    private static String scanDate(){
        while (true){
            try {
                String date=scanner.nextLine();
                String[] dateSplit=date.split("-");
                long totalCharacters = date.chars().filter(ch -> ch == '-').count();

                if(dateSplit.length==3&&totalCharacters<3){

                    if (Integer.parseInt(dateSplit[0]) > 1900 && Integer.parseInt(dateSplit[0]) <= 2022) {
                        if (Integer.parseInt(dateSplit[1]) <= 9 && !dateSplit[1].contains("0") || Integer.parseInt(dateSplit[2]) <= 9 && !dateSplit[2].contains("0")) {
                            System.out.println("Make sure the format is correct. (2000-01-01)");
                        } else {
                            if (Integer.parseInt(dateSplit[1]) <= 12 && Integer.parseInt(dateSplit[1]) >= 1) {
                            if (Integer.parseInt(dateSplit[2]) <= daysInMonth(Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0])) && Integer.parseInt(dateSplit[2]) >= 1) {
                                return date;
                            } else {
                                System.out.println("Make sure the format is correct. (2000-01-01)");
                            }
                        } else {
                            System.out.println("Make sure the format is correct. (2000-01-01)");
                        }
                    }
                    }else{
                        System.out.println("Make sure the format is correct. (2000-01-01)");
                    }
                }else{
                    System.out.println("Make sure the format is correct. (2000-01-01)");
                }
            }catch (Exception e){
                System.out.println("Make sure the format is correct. (2000-01-01)");
            }
        }


    }
    private static void deleteBill(ArrayList<Bill> bills) {
        System.out.print("Enter the document number of the bill what you want to delete: ");

        String documentNumber = modOrDel();
        for (Bill bill : bills) {
            if (bill.getDocumentNumber().equals(documentNumber)) {
                bills.remove(bill);
                System.out.println("Bill is successfully deleted.");
                return;
            }
        }
        System.out.println("There is no bill with this document number.");
    }
    private static void modifyBill(ArrayList<Bill> bills){
        System.out.print("Enter the document number of the bill what you want to modify: ");
        String documentNumber=modOrDel();
        for (Bill bill : bills) {
            if (bill.getDocumentNumber().equals(documentNumber)) {

                System.out.println("Enter name of the buyer what you want to modify.");
                String buyer=scanner.nextLine();
                System.out.println("Enter date of new bill what you want to modify.(2022-01-01)");
                String date=scanDate();
                System.out.println("Enter fulfillment of  bill what you want to modify.(2022-01-01)");
                String fulfillment=scanDate();
                System.out.println("Enter deadline of  bill what you want to modify.(2022-01-01)");
                String deadline=scanDate();
                System.out.println("Enter gross of  bill what you want to modify.");
                Integer gross=scanInt();
                scanner.nextLine();
                System.out.println("Enter delay of  bill what you want to modify.");
                Integer delay=scanInt();
                scanner.nextLine();
                int VAT= (int) (gross*0.27);
                int net=(int) (gross*0.73);
                bills.set(bills.indexOf(bill),new Bill(documentNumber,buyer,date,fulfillment,deadline,gross,VAT,net,delay));
                System.out.println("Bill is successfully modified.");
                return;
            }
        }
        System.out.println("There is no bill with this document number.");
    }
    private static void tableHeadRow(){
        System.out.print("+");
        row();
        System.out.print("\r\n|");
        System.out.printf("%19s%18s%20s%23s%18s%14s%15s%16s%13s%4s\r\n","Document Number","Buyer","Date","Fulfillment","Deadline","Net","VAT","Gross","Delay","|");

    }
    private static void tableRow(String documentNumber,String buyer,String date,String fulfillment,
                                 String deadline,Integer net,Integer VAT,Integer gross,Integer delay){
        System.out.print("+");
        row();
        System.out.print("\r\n|");
        System.out.printf("%16s%29s%19s%20s%19s%13d$%15d$%13d$%8d day%s\r\n",documentNumber,buyer,date,fulfillment,deadline,net,VAT,gross,delay,"|");


    }
    private static void rowMaker(int hyphenNumber){

        for (int i=0; i<hyphenNumber;i++){
            System.out.print("-");
        }
        System.out.print("+");
    }
    private static void row(){
        rowMaker(23);
        rowMaker(21);
        rowMaker(18);
        rowMaker(19);
        rowMaker(18);
        rowMaker(13);
        rowMaker(15);
        rowMaker(13);
        rowMaker(11);
    }
    public static void saveUsersToXml(ArrayList<Bill> bills, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("bills");
            document.appendChild(rootElement);

            for (Bill bill : bills) {
                Element userElement = document.createElement("bill");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "documentNumber", bill.getDocumentNumber());
                createChildElement(document, userElement, "buyer",bill.getBuyer());
                createChildElement(document, userElement, "date", bill.getDate());
                createChildElement(document, userElement, "fulfillment", bill.getFulfillment());
                createChildElement(document, userElement, "deadline", bill.getDeadline());
                createChildElement(document, userElement, "net", String.valueOf(bill.getNet()));
                createChildElement(document, userElement, "VAT", String.valueOf(bill.getVAT()));
                createChildElement(document, userElement, "VAT", String.valueOf(bill.getGross()));
                createChildElement(document, userElement, "VAT", String.valueOf(bill.getDelay()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Integer scanInt(){
         while(true){
             try{
                 int number=scanner.nextInt();
                 return number;
             }catch (Exception e){
                 System.out.println("Make sure you entered Integer.");
                 scanner.nextLine();
             }
         }
    }
    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
