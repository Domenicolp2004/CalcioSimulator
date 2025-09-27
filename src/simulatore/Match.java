package simulatore;

public 	class Match {
    String home, away;
    int goalsHome, goalsAway;

    Match(String home, String away, int goalsHome, int goalsAway) {
        this.home = home;
        this.away = away;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    @Override
    public String toString() {
        return home + " " + goalsHome + " - " + goalsAway + " " + away;
    }
}
