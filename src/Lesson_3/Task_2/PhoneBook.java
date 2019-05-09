package Lesson_3.Task_2;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<PhoneNumber, Person> pBook;

    PhoneBook() {
        this.pBook = new HashMap<>(32);
    }

    public String get(String lastName) {
        ArrayList<String> entries = new ArrayList<>();
        for (PhoneNumber pN : pBook.keySet()) {
            if (lastName.equals(pBook.get(pN).toString())) {
                entries.add(pN.toString());
            }
        }

        if (entries.isEmpty()) {
            return null;
        }

        StringBuilder msg = new StringBuilder(lastName + ": ");
        for (int i = 0; i < entries.size(); i++) {
            if (i == entries.size() - 1) {
                msg.append(entries.get(i)).append(".");
            } else {
                msg.append(entries.get(i)).append(", ");
            }
        }

        return msg.toString();
    }

    public String getByPhone(String phoneNumber) {
        return PhoneNumber.parsePhoneNumber(phoneNumber).toString() +
                ": " +
                pBook.get(new PhoneNumber(phoneNumber)).toString();
    }

    public void add(PhoneNumber pN, Person p) {
        this.pBook.put(pN, p);
    }

    public void add(PhoneNumber pN, String p) {
        Person person = new Person(p);
        this.pBook.put(pN, person);
    }

    public void add(String pN, Person p) {
        PhoneNumber phoneNumber = PhoneNumber.parsePhoneNumber(pN);
        this.pBook.put(phoneNumber, p);
    }

    public void add(String pN, String p) {
        PhoneNumber phoneNumber = PhoneNumber.parsePhoneNumber(pN);
        Person person = new Person(p);
        this.pBook.put(phoneNumber, person);
    }
}
