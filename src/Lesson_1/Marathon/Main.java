package Lesson_1.Marathon;

public class Main {
    public static void main(String[] args) {
        //Course c = new Course(...); // Создаем полосу препятствий
        //Team team = new Team(...);  // Создаем команду
        //c.doIt(team);               // Просим команду пройти полосу
        //team.showResults();         // Показываем результаты

        Team team1 = new Team("Люди", new Human("Вася"), new Human("Петя"), new Human("Коля"), new Human("Коля"));
        Team team2 = new Team("Животные", new Cat("Барсик"), new Dog("Жучка"), new Cat("Мурзик"), new Dog("Рекс"));
        Obstacle water1 = new Water(10);
        Obstacle cross1 = new Cross(10);
        Obstacle wall1 = new Wall(1);
        Course course = new Course(team1, team2, water1, cross1,wall1);
        course.doIt();
        team1.showResults();
        team2.showResults();
    }
}