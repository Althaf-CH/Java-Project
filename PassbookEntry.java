public class PassbookEntry {
    int id;
    int memberId;
    String date;
    double amount;

    public PassbookEntry(int id, int memberId, String date, double amount) {
        this.id = id;
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }
}
