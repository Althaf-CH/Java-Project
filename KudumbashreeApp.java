import java.util.Scanner;

public class KudumbashreeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberRepository memberRepo = new MemberRepository();
        MeetingRepository meetingRepo = new MeetingRepository();
        PassbookRepository passbookRepo = new PassbookRepository();
        ProductRepository productRepo = new ProductRepository();

        while (true) {
            System.out.println("\n--- Kudumbashree Data Entry App ---");
            System.out.println("1. Members");
            System.out.println("2. Meetings");
            System.out.println("3. Passbook");
            System.out.println("4. Products");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("1. Add Member\n2. View Members");
                    int mChoice = sc.nextInt(); sc.nextLine();
                    if (mChoice == 1) {
                        System.out.print("Name: "); String name = sc.nextLine();
                        System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
                        System.out.print("Skill: "); String skill = sc.nextLine();
                        memberRepo.addMember(name, age, skill);
                    } else {
                        memberRepo.viewMembers();
                    }
                }
                case 2 -> {
                    System.out.println("1. Add Meeting\n2. View Meetings");
                    int meetChoice = sc.nextInt(); sc.nextLine();
                    if (meetChoice == 1) {
                        System.out.print("Date: "); String date = sc.nextLine();
                        System.out.print("Description: "); String desc = sc.nextLine();
                        meetingRepo.addMeeting(date, desc);
                    } else {
                        meetingRepo.viewMeetings();
                    }
                }
                case 3 -> {
                    System.out.println("1. Add Entry\n2. View Entries");
                    int pChoice = sc.nextInt(); sc.nextLine();
                    if (pChoice == 1) {
                        System.out.print("Member ID: "); int memId = sc.nextInt(); sc.nextLine();
                        System.out.print("Date: "); String date = sc.nextLine();
                        System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
                        passbookRepo.addEntry(memId, date, amt);
                    } else {
                        passbookRepo.viewEntries();
                    }
                }
                case 4 -> {
                    System.out.println("1. Add Product\n2. View Products");
                    int prodChoice = sc.nextInt(); sc.nextLine();
                    if (prodChoice == 1) {
                        System.out.print("Name: "); String pname = sc.nextLine();
                        System.out.print("Price: "); double price = sc.nextDouble(); sc.nextLine();
                        System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
                        productRepo.addProduct(pname, price, qty);
                    } else {
                        productRepo.viewProducts();
                    }
                }
                case 0 -> {
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}
