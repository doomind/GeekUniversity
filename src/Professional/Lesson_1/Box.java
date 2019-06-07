package Professional.Lesson_1;

import java.util.Iterator;
import java.util.LinkedList;

public class Box<T extends Fruit> {
    private LinkedList<T> fruits;

    public Box() {
        this.fruits = new LinkedList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void move(Box<T> another) {
        Iterator<T> i = fruits.iterator();
        while (i.hasNext()) {
            another.add(i.next());
            i.remove();
        }
    }

    public float getWeight() {
        float weight = 0.0f;
        Iterator<T> i = fruits.iterator();
        while (i.hasNext()) {
            weight += i.next().getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> another) {
        return this.getWeight() == another.getWeight();
    }
}