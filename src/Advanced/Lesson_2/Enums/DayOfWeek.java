package Advanced.Lesson_2.Enums;

public enum DayOfWeek {
    MONDAY(1, "Понедельник", "Monday", 8),
    TUESDAY(2,"Вторник", "Tuesday", 8),
    WEDNESDAY(3, "Среда", "Wednesday", 8),
    THURSDAY(4, "Четверг", "Thursday", 8),
    FRIDAY(5, "Пятница", "Friday", 8),
    SATURDAY(6, "Суббота", "Saturday", 0),
    SUNDAY(7, "Воскресенье", "Sunday", 0);

    private int numInWeek;
    private String rusTitle;
    private String engTitle;
    private int workHours;

    DayOfWeek(int numInWeek, String rusTitle, String engTitle, int workHours) {
        this.numInWeek = numInWeek;
        this.rusTitle = rusTitle;
        this.engTitle = engTitle;
        this.workHours = workHours;
    }

    public int getNumInWeek() {
        return numInWeek;
    }

    public String getRusTitle() {
        return rusTitle;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public int getWorkHours() {
        return workHours;
    }
}
