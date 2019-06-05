package Advanced.Lesson_3.Task_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> cities = new ArrayList<>();

        cities.add("Москва");
        cities.add("Берлин");
        cities.add("Париж");
        cities.add("Лондон");
        cities.add("Вена");
        cities.add("Брюссель");
        cities.add("Прага");
        cities.add("Мадрид");
        cities.add("Лиссабон");
        cities.add("Рим");
        cities.add("Будапешт");
        cities.add("Осло");
        cities.add("Хельсинки");
        cities.add("Копенгаген");
        cities.add("Москва");
        cities.add("Лиссабон");
        cities.add("Брюссель");
        cities.add("Париж");
        cities.add("Мадрид");
        cities.add("Москва");

        HashSet<String> citiesUnic = new HashSet<>(cities);
        Iterator<String> citiesIter = cities.iterator();
        HashMap<String,String> citiesRepeat = new HashMap<>();

        while (citiesIter.hasNext()) {
            String city = citiesIter.next();
            int num;
            if (citiesRepeat.containsKey(city)) {
                num = Integer.parseInt(citiesRepeat.get(city));
                num++;
                citiesRepeat.put(city, Integer.toString(num));
            } else {
                num = 1;
                citiesRepeat.put(city, Integer.toString(num));
            }
        }

        System.out.println("Исходный массив: " + cities);
        System.out.println("Уникальные значения: " + citiesUnic);
        System.out.println("Повторения: " + citiesRepeat);
    }
}
