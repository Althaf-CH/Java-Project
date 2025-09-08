import java.util.Scanner;

public class KudumbashreeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MemberRepository memberRepo = new MemberRepository();
        MeetingRepository meetingRepo = new MeetingRepository();
        PassbookRepository passbookRepo = new PassbookRepository();
        ProductRepository productRepo = new ProductRepository();

        while (true) {
            System.out.println("\n===== Kudumbashree Data Entry App =====");
            System.out.println("1. Members");
            System.out.println("2. Meetings");
            System.out.println("3. Passbook");
            System.out.println("4. Products");
            System.out.println("5. Overview (View All Data)");
            System.out.println("0. Exit");
            System.out.print("👉 Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                // ===== MEMBERS =====
                case 1 -> {
                    System.out.println("\n--- Members ---");
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
                            System.out.print("Member ID: "); int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Name: "); String newName = sc.nextLine();
                            System.out.print("New Age: "); int newAge = sc.nextInt(); sc.nextLine();
                            System.out.print("New Skill: "); String newSkill = sc.nextLine();
                            memberRepo.updateMember(id, newName, newAge, newSkill);
                        }
                        case 4 -> {
                            System.out.print("Member ID: "); int id = sc.nextInt(); sc.nextLine();
                            memberRepo.deleteMember(id);
                        }
                    }
                }

                // ===== MEETINGS =====
                case 2 -> {
                    System.out.println("\n--- Meetings ---");
                    System.out.println("1. Add Meeting\n2. View Meetings\n3. Update Meeting\n4. Delete Meeting");
                    int meetChoice = sc.nextInt(); sc.nextLine();
                    switch (meetChoice) {
                        case 1 -> {
                            System.out.print("Date (DD-MM-YYYY): "); String date = sc.nextLine();
                            System.out.print("Description: "); String desc = sc.nextLine();
                            meetingRepo.addMeeting(date, desc);
                        }
                        case 2 -> meetingRepo.viewMeetings();
                        case 3 -> {
                            System.out.print("Meeting ID: "); int mid = sc.nextInt(); sc.nextLine();
                            System.out.print("New Date: "); String newDate = sc.nextLine();
                            System.out.print("New Description: "); String newDesc = sc.nextLine();
                            meetingRepo.updateMeeting(mid, newDate, newDesc);
                        }
                        case 4 -> {
                            System.out.print("Meeting ID: "); int mid = sc.nextInt(); sc.nextLine();
                            meetingRepo.deleteMeeting(mid);
                        }
                    }
                }

                // ===== PASSBOOK =====
                case 3 -> {
                    System.out.println("\n--- Passbook ---");
                    System.out.println("1. Add Entry\n2. View Entries\n3. Update Entry\n4. Delete Entry\n5. View Total Balance");
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
                            System.out.print("Entry ID: "); int eid = sc.nextInt(); sc.nextLine();
                            System.out.print("New Member ID: "); int newMemId = sc.nextInt(); sc.nextLine();
                            System.out.print("New Date: "); String newDate = sc.nextLine();
                            System.out.print("New Amount: "); double newAmt = sc.nextDouble(); sc.nextLine();
                            passbookRepo.updateEntry(eid, newMemId, newDate, newAmt);
                        }
                        case 4 -> {
                            System.out.print("Entry ID: "); int eid = sc.nextInt(); sc.nextLine();
                            passbookRepo.deleteEntry(eid);
                        }
                        case 5 -> passbookRepo.getTotalBalance();
                    }
                }

                // ===== PRODUCTS =====
                case 4 -> {
                    System.out.println("\n--- Products ---");
                    System.out.println("1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product\n5. Search Product\n6. Update Stock");
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
                            System.out.print("Product ID: "); int pid = sc.nextInt(); sc.nextLine();
                            System.out.print("New Name: "); String nName = sc.nextLine();
                            System.out.print("New Price: "); double nPrice = sc.nextDouble(); sc.nextLine();
                            System.out.print("New Quantity: "); int nQty = sc.nextInt(); sc.nextLine();
                            productRepo.updateProduct(pid, nName, nPrice, nQty);
                        }
                        case 4 -> {
                            System.out.print("Product ID: "); int pid = sc.nextInt(); sc.nextLine();
                            productRepo.deleteProduct(pid);
                        }
                        case 5 -> {
                            System.out.print("Keyword: "); String kw = sc.nextLine();
                            productRepo.searchProducts(kw);
                        }
                        case 6 -> {
                            System.out.print("Product ID: "); int pid = sc.nextInt();
                            System.out.print("Quantity Change (+/-): "); int delta = sc.nextInt(); sc.nextLine();
                            productRepo.updateStock(pid, delta);
                        }
                    }
                }

                // ===== OVERVIEW =====
                case 5 -> {
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

