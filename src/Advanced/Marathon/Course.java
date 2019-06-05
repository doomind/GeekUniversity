package Advanced.Marathon;

public class Course {
    private Obstacle[] obstacles;
    private Team[] teams;

    Course (Team team1, Team team2, Obstacle... obstacles) {
        this.teams = new Team[]{team1, team2};
        this.obstacles = obstacles;
    }

    public void doIt () {
        for (Obstacle obstacle:
             this.obstacles) {
            for (Team team:
                 teams) {
                for (Competitor competitor:
                     team.competitors) {
                        obstacle.doIt(competitor);
                }
            }
        }
    }
}
