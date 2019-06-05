package Advanced.Lesson_3.Task_2;

import java.util.Objects;

public class Person {
    private String lastName;

    Person(String s) {
        this.lastName = s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName);
    }

    @Override
    public String toString() {
        return String.valueOf(lastName);
    }
}
