import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Hiển thị menu
            System.out.println("==== QUẢN LÝ DANH BẠ ====");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới danh bạ");
            System.out.println("3. Cập nhật danh bạ");
            System.out.println("4. Xóa danh bạ");
            System.out.println("5. Tìm kiếm danh bạ");
            System.out.println("6. Sắp xếp danh bạ theo họ tên");
            System.out.println("7. Đọc danh bạ từ file CSV");
            System.out.println("8. Ghi danh bạ vào file CSV");
            System.out.println("9. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine(); // tiêu thụ ký tự newline

            switch (choice) {
                case 1:
                    manager.viewContacts();
                    break;
                case 2:
                    Contact newContact = inputContact(sc);
                    manager.addContact(newContact);
                    break;
                case 3:
                    System.out.print("Nhập số điện thoại của danh bạ cần cập nhật: ");
                    String updatePhone = sc.nextLine();
                    Contact updatedContact = inputContact(sc);
                    manager.updateContact(updatePhone, updatedContact);
                    break;
                case 4:
                    System.out.print("Nhập số điện thoại của danh bạ cần xóa: ");
                    String deletePhone = sc.nextLine();
                    manager.deleteContact(deletePhone);
                    break;
                case 5:
                    System.out.print("Nhập từ khóa (số điện thoại hoặc họ tên): ");
                    String keyword = sc.nextLine();
                    manager.searchContacts(keyword);
                    break;
                case 6:
                    manager.sortContacts();
                    break;
                case 7:
                    System.out.println("Cảnh báo: Đọc từ file sẽ xoá toàn bộ danh bạ hiện có.");
                    System.out.print("Bạn có chắc (Y/N)? ");
                    String confirmRead = sc.nextLine();
                    if (confirmRead.equalsIgnoreCase("Y")) {
                        System.out.print("Nhập đường dẫn file CSV cần đọc: ");
                        String pathRead = sc.nextLine();
                        manager.readFromFile(pathRead);
                    }
                    break;
                case 8:
                    System.out.println("Cảnh báo: Ghi vào file sẽ cập nhật toàn bộ nội dung file.");
                    System.out.print("Bạn có chắc (Y/N)? ");
                    String confirmWrite = sc.nextLine();
                    if (confirmWrite.equalsIgnoreCase("Y")) {
                        System.out.print("Nhập đường dẫn file CSV cần lưu: ");
                        String pathWrite = sc.nextLine();
                        manager.writeToFile(pathWrite);
                    }
                    break;
                case 9:
                    exit = true;
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    // Phương thức nhập thông tin danh bạ từ người dùng
    public static Contact inputContact(Scanner sc) {
        String phoneNumber = "";
        String fullName = "";
        String group = "";
        String gender = "";
        String address = "";
        String birthday = "";
        String email = "";

        // Kiểm tra số điện thoại: bắt buộc và phải là 10 chữ số
        while (true) {
            System.out.print("Nhập số điện thoại (10 chữ số): ");
            phoneNumber = sc.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("Số điện thoại là bắt buộc. Vui lòng nhập lại.");
                continue;
            }
            if (!phoneNumber.matches("^[0-9]{10}$")) {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập đúng 10 chữ số.");
                continue;
            }
            break;
        }

        // Kiểm tra họ tên: bắt buộc
        while (true) {
            System.out.print("Nhập họ tên: ");
            fullName = sc.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("Họ tên là bắt buộc. Vui lòng nhập lại.");
                continue;
            }
            break;
        }

        // Các trường khác không bắt buộc nhưng vẫn cho nhập:
        System.out.print("Nhập nhóm: ");
        group = sc.nextLine().trim();

        System.out.print("Nhập giới tính: ");
        gender = sc.nextLine().trim();

        System.out.print("Nhập địa chỉ: ");
        address = sc.nextLine().trim();

        System.out.print("Nhập ngày sinh (dd/mm/yyyy): ");
        birthday = sc.nextLine().trim();

        // Kiểm tra email
        while (true) {
            System.out.print("Nhập email: ");
            email = sc.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email là bắt buộc. Vui lòng nhập ");
                continue;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                continue;
            }
            break;
        }

        return new Contact(phoneNumber, group, fullName, gender, address, birthday, email);
    }

}
