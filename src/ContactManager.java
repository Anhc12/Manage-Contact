import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ContactManager implements iContact {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    @Override
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int count = 0;

        for (Contact contact : contacts) {
            System.out.println(contact);
            count++;

            // Khi đã hiển thị 5 mục, đợi người dùng nhấn Enter, trừ khi đây là mục cuối cùng.
            if (count % 5 == 0 && count < contacts.size()) {
                System.out.println("Nhấn Enter để xem tiếp...");
                sc.nextLine(); // Đợi người dùng nhấn Enter
            }
        }
    }


    @Override
    public void addContact(Contact contact) {
        // Bạn có thể kiểm tra dữ liệu hợp lệ (số điện thoại, email, vv) tại đây
        contacts.add(contact);
        System.out.println("Thêm danh bạ thành công!");
    }

    @Override
    public void updateContact(String phoneNumber, Contact newContact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                contacts.set(i, newContact);
                System.out.println("Cập nhật danh bạ thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy danh bạ với số điện thoại: " + phoneNumber);
    }

    @Override
    public void deleteContact(String phoneNumber) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Bạn có chắc muốn xoá danh bạ này? (Y/N)");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    contacts.remove(i);
                    System.out.println("Xoá danh bạ thành công!");
                } else {
                    System.out.println("Huỷ xoá danh bạ.");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy danh bạ với số điện thoại: " + phoneNumber);
    }

    @Override
    public void searchContacts(String keyword) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().contains(keyword) ||
                    contact.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy danh bạ phù hợp với từ khóa: " + keyword);
        }
    }

    @Override
    public void sortContacts() {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getFullName().compareToIgnoreCase(c2.getFullName());
            }
        });
        System.out.println("Sắp xếp danh bạ theo họ tên thành công!");
    }

    @Override
    public void readFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File không tồn tại!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Giả sử dòng đầu là header
            contacts.clear();
            while ((line = br.readLine()) != null) {
                // Giả sử file CSV có định dạng: phone,group,fullName,gender,address,birthday,email
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                    contacts.add(contact);
                }
            }
            System.out.println("Đọc danh bạ từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    @Override
    public void writeToFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            // Ghi header
            pw.println("phoneNumber,group,fullName,gender,address,birthday,email");
            for (Contact contact : contacts) {
                pw.printf("%s,%s,%s,%s,%s,%s,%s%n",
                        contact.getPhoneNumber(),
                        contact.getGroup(),
                        contact.getFullName(),
                        contact.getGender(),
                        contact.getAddress(),
                        contact.getBirthday(),
                        contact.getEmail());
            }
            System.out.println("Ghi danh bạ vào file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}