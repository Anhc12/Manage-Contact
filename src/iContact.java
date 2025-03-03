import java.util.List;

public interface iContact {
    void viewContacts();
    void addContact(Contact contact);
    void updateContact(String phoneNumber, Contact newContact);
    void deleteContact(String phoneNumber);
    void searchContacts(String keyword);
    void sortContacts();
    void readFromFile(String filePath);
    void writeToFile(String filePath);
}

