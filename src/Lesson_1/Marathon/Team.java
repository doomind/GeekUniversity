package Lesson_1.Marathon;

public class Team {
    private String name;
    Competitor[] competitors;

    Team (String name, Competitor... competitors) {
        this.name = name;
        this.competitors = competitors;
    }

    public void showResults() {
        System.out.println("Участники комманды \"" + name + "\":");
        int scores = 0;
        for (Competitor competitor:
             competitors){
            competitor.info();
            scores = competitor.isOnDistance() ? ++scores : scores;
        }
        String scoresSuffix;
        if (Integer.toString(scores).matches("^\\d*[2-9][1]$")) {
            scoresSuffix = "очко";
        } else if (Integer.toString(scores).matches("^\\d*[2-9]?[2-4]$")) {
            scoresSuffix = "очка";
        } else {
            scoresSuffix = "очков";
        }
        System.out.println("Комманда \"" + name + "\" набрала " + scores + " " + scoresSuffix + ".");
    }
}