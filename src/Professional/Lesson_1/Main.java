package Professional.Lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Задание №1.
        System.out.println("Задание №1.");
        ArrayList<String> arr1 = new ArrayList<>();
        arr1.add("First");
        arr1.add("Second");
        arr1.add("Third");
        arr1.add("Fourth");
        System.out.print("До перестановки: ");
        System.out.println(arr1.toString());
        switchArrayElements(1, 3, arr1);
        System.out.print("После перестановки: ");
        System.out.println(arr1.toString());

        // Задание №2.
        System.out.println("Задание №2.");
        String[] arr2 = {"First", "Second", "Third", "Fourth"};
        System.out.print("Массив: ");
        System.out.println(arr2);
        ArrayList<?> list = convertArrayToArrayList(arr2);
        System.out.print("ArrayList: ");
        System.out.println(list);

        // Задание №3.
        System.out.println("Задание №3.");
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        for (int i = 0; i < 100 * Math.random(); i++) {
            orangeBox1.add(new Orange());
        }
        System.out.println("orangeBox1 весит: " + orangeBox1.getWeight());

        for (int i = 0; i < 100 * Math.random(); i++) {
            orangeBox2.add(new Orange());
        }
        System.out.println("orangeBox2 весит: " + orangeBox2.getWeight());

        for (int i = 0; i < 100 * Math.random(); i++) {
            appleBox1.add(new Apple());
        }
        System.out.println("appleBox1 весит: " + appleBox1.getWeight());

        for (int i = 0; i < 100 * Math.random(); i++) {
            appleBox2.add(new Apple());
        }
        System.out.println("appleBox2 весит: " + appleBox2.getWeight());

        System.out.println("Сравниваем orangeBox1 и appleBox1: " + orangeBox1.compare(appleBox1));

        System.out.println("Пересыпаем orangeBox1 в orangeBox2...");
        orangeBox1.move(orangeBox2);
        System.out.println("orangeBox1 весит: " + orangeBox1.getWeight());
        System.out.println("orangeBox2 весит: " + orangeBox2.getWeight());

        System.out.println("Пересыпаем appleBox2 в appleBox1...");
        appleBox2.move(appleBox1);
        System.out.println("appleBox1 весит: " + appleBox1.getWeight());
        System.out.println("appleBox2 весит: " + appleBox2.getWeight());
        System.out.println("Сравниваем orangeBox1 и appleBox2: " + orangeBox1.compare(appleBox2));
    }

    private static <T extends List> void switchArrayElements(int source, int destination, T array)
            throws IndexOutOfBoundsException {

        Object tmpObj = array.get(destination);
        array.set(destination, array.get(source));
        array.set(source, tmpObj);
    }

    private static ArrayList<?> convertArrayToArrayList(Object[] arr) {
        ArrayList<?> list = new ArrayList<Object>(Arrays.asList(arr));
        return list;
    }
}
