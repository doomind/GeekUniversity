package Advanced.Lesson_3.Task_2;

public class Main {
    public static void main(String[] args) {
        PhoneNumber n1 = new PhoneNumber(7,911,7541236);
        PhoneNumber n2 = new PhoneNumber("+7 (906) 1234567");
        PhoneNumber n3 = new PhoneNumber("7 495 1234000");
        PhoneNumber n4 = new PhoneNumber("+49(030)12345678");
        PhoneNumber n5 = new PhoneNumber(1, 213, 5550123);

        PhoneBook pB = new PhoneBook();
        pB.add(n1, "Иванов");
        pB.add(n2, "Петров");
        pB.add(n3, "Петров");
        pB.add(n4, "Schneider");
        pB.add(n5, "Johnson");

        System.out.println(pB.get("Петров"));
        System.out.println(pB.get("Johnson"));
        System.out.println(pB.getByPhone("+49 30 12345678"));
        System.out.println(pB.getByPhone("+7(495)1234000"));
    }
}
