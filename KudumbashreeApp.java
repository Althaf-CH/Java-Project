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
            System.out.println("5. View All Tables ✅");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {   // 👤 Members
                    System.out.println("1. Add Member\n2. View Members\n3. Update Member\n4. Delete Member");
                    int mChoice = sc.nextInt(); sc.nextLine();
                    switch (mChoice) {
                        case 1 -> {
                            System.out.print("Name: "); String name = sc.nextLine();
                            System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
                            System.out.print("Skill: "); String skill = sc.nextLine();
                            memberRepo.addMember(name, age, skill);
                        }
                        case 2 -> memberRepo.viewMembers();
                        case 3 -> {
                            System.out.print("Enter Member ID to update: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Name: "); String nName = sc.nextLine();
                            System.out.print("New Age: "); int nAge = sc.nextInt(); sc.nextLine();
                            System.out.print("New Skill: "); String nSkill = sc.nextLine();
                            memberRepo.updateMember(id, nName, nAge, nSkill);
                        }
                        case 4 -> {
                            System.out.print("Enter Member ID to delete: "); int id = sc.nextInt(); sc.nextLine();
                            memberRepo.deleteMember(id);
                        }
                        default -> System.out.println("❌ Invalid choice!");
                    }
                }

                case 2 -> {   // 📅 Meetings
                    System.out.println("1. Add Meeting\n2. View Meetings\n3. Update Meeting\n4. Delete Meeting");
                    int meetChoice = sc.nextInt(); sc.nextLine();
                    switch (meetChoice) {
                        case 1 -> {
                            System.out.print("Date (DD-MM-YYYY):"); String date = sc.nextLine();
                            System.out.print("Description: "); String desc = sc.nextLine();
                            meetingRepo.addMeeting(date, desc);
                        }
                        case 2 -> meetingRepo.viewMeetings();
                        case 3 -> {
                            System.out.print("Enter Meeting ID to update: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Date (DD-MM-YYYY): "); String nDate = sc.nextLine();
                            System.out.print("New Description: "); String nDesc = sc.nextLine();
                            meetingRepo.updateMeeting(id, nDate, nDesc);
                        }
                        case 4 -> {
                            System.out.print("Enter Meeting ID to delete: "); int id = sc.nextInt(); sc.nextLine();
                            meetingRepo.deleteMeeting(id);
                        }
                        default -> System.out.println("❌ Invalid choice!");
                    }
                }

                case 3 -> {   // 💰 Passbook
                    System.out.println("1. Add Entry\n2. View Entries\n3. Update Entry\n4. Delete Entry");
                    int pChoice = sc.nextInt(); sc.nextLine();
                    switch (pChoice) {
                        case 1 -> {
                            System.out.print("Member ID: "); int memId = sc.nextInt(); sc.nextLine();
                            System.out.print("Date (DD-MM-YYYY): "); String date = sc.nextLine();
                            System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
                            passbookRepo.addEntry(memId, date, amt);
                        }
                        case 2 -> passbookRepo.viewEntries();
                        case 3 -> {
                            System.out.print("Enter Entry ID to update: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Member ID: "); int nMemId = sc.nextInt(); sc.nextLine();
                            System.out.print("New Date (DD-MM-YYYY): "); String nDate = sc.nextLine();
                            System.out.print("New Amount: "); double nAmt = sc.nextDouble(); sc.nextLine();
                            passbookRepo.updateEntry(id, nMemId, nDate, nAmt);
                        }
                        case 4 -> {
                            System.out.print("Enter Entry ID to delete: "); int id = sc.nextInt(); sc.nextLine();
                            passbookRepo.deleteEntry(id);
                        }
                        default -> System.out.println("❌ Invalid choice!");
                    }
                }

                case 4 -> {   // 📦 Products
                    System.out.println("1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product");
                    int prodChoice = sc.nextInt(); sc.nextLine();
                    switch (prodChoice) {
                        case 1 -> {
                            System.out.print("Name: "); String pname = sc.nextLine();
                            System.out.print("Price: "); double price = sc.nextDouble(); sc.nextLine();
                            System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
                            productRepo.addProduct(pname, price, qty);
                        }
                        case 2 -> productRepo.viewProducts();
                        case 3 -> {
                            System.out.print("Enter Product ID to update: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Name: "); String nName = sc.nextLine();
                            System.out.print("New Price: "); double nPrice = sc.nextDouble(); sc.nextLine();
                            System.out.print("New Quantity: "); int nQty = sc.nextInt(); sc.nextLine();
                            productRepo.updateProduct(id, nName, nPrice, nQty);
                        }
                        case 4 -> {
                            System.out.print("Enter Product ID to delete: "); int id = sc.nextInt(); sc.nextLine();
                            productRepo.deleteProduct(id);
                        }
                        default -> System.out.println("❌ Invalid choice!");
                    }
                }

                case 5 -> {   // 👀 View All
                    System.out.println("\n--- All Data Overview ---");
                    System.out.println("👤 Members:");
                    memberRepo.viewMembers();
                    System.out.println("\n📅 Meetings:");
                    meetingRepo.viewMeetings();
                    System.out.println("\n💰 Passbook:");
                    passbookRepo.viewEntries();
                    System.out.println("\n📦 Products:");
                    productRepo.viewProducts();
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