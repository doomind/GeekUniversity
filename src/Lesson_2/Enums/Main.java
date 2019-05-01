package Lesson_2.Enums;

public class Main {
    public static void main(String[] args) {
        DayOfWeek mon = DayOfWeek.TUESDAY;
        System.out.println(getWorkingHours(mon));
    }

    private static int getWorkingHours(DayOfWeek fDay) {
        int result = 0;

        for (DayOfWeek d:
            DayOfWeek.values()) {
            if (d.getNumInWeek() >= fDay.getNumInWeek()) {
                result += d.getWorkHours();
            }
        }
        return result;
    }
}
