package manager;

public class Bill {
    private String documentNumber;
    private String buyer;
    private String date;
    private String fulfillment;
    private String deadline;
    private int net;
    private int VAT;
    private int gross;
    private int delay;

    public Bill(String documentNumber, String buyer, String date, String fulfillment, String deadline, int net, int VAT, int gross, int delay) {
        this.documentNumber = documentNumber;
        this.buyer = buyer;
        this.date = date;
        this.fulfillment = fulfillment;
        this.deadline = deadline;
        this.net = net;
        this.VAT = VAT;
        this.gross = gross;
        this.delay = delay;
    }

    public String  getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(String fulfillment) {
        this.fulfillment = fulfillment;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getVAT() {
        return VAT;
    }

    public void setVAT(int VAT) {
        this.VAT = VAT;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "documentNumber='" + documentNumber + '\'' +
                ", buyer='" + buyer + '\'' +
                ", date='" + date + '\'' +
                ", fulfillment='" + fulfillment + '\'' +
                ", deadline='" + deadline + '\'' +
                ", net=" + net +
                ", VAT=" + VAT +
                ", gross=" + gross +
                ", delay=" + delay +
                '}';
    }
}
