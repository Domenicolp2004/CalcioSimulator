package simulatore;

import java.util.*;
	class Team {
	    String name;
	    double avgGoalsFor;     // media gol fatti a partita
	    double avgGoalsAgainst; 
	    int points = 0;
	    int goalsFor = 0;
	    int goalsAgainst = 0;

	    Team(String name) {
	        this.name = name;
	    }

	    Team(String name, double avgGoalsFor, double avgGoalsAgainst) {
	        this.name = name;
	        this.avgGoalsFor = avgGoalsFor;
	        this.avgGoalsAgainst = avgGoalsAgainst;
	    }
	    
	    void updateMatch(int scored, int conceded) {
	        goalsFor += scored;
	        goalsAgainst += conceded;
	        if (scored > conceded) points += 3;
	        else if (scored == conceded) points += 1;
	    }

	    @Override
	    public String toString() {
	        return name + " | Punti: " + points + " | GF: " + goalsFor + " | GS: " + goalsAgainst;
	    }
	}
	
	
	

	public class LeagueSimulator {
	    
		public static int poisson(double lambda, Random rand) {
	        double L = Math.exp(-lambda);
	        int k = 0;
	        double p = 1.0;
	        do {
	            k++;
	            p *= rand.nextDouble();
	        } while (p > L);
	        return k - 1;
	    }
	    public static void main(String[] args) {
	        // 1. Lista squadre (puoi caricarle anche da file in seguito)
	        List<Team> teams = new ArrayList<>();
	        teams.add(new Team("Atalanta"));
	        teams.add(new Team("Bologna"));
	        teams.add(new Team("Cagliari"));
	        teams.add(new Team("Como"));
	        teams.add(new Team("Cremonese"));
	        teams.add(new Team("Fiorentina"));
	        teams.add(new Team("Genoa"));
	        teams.add(new Team("Hellas Verona"));
	        teams.add(new Team("Inter"));
	        teams.add(new Team("Juventus"));
	        teams.add(new Team("Lazio"));
	        teams.add(new Team("Lecce"));
	        teams.add(new Team("Milan"));
	        teams.add(new Team("Napoli"));
	        teams.add(new Team("Parma"));
	        teams.add(new Team("Pisa"));
	        teams.add(new Team("Roma"));
	        teams.add(new Team("Sassuolo"));
	        teams.add(new Team("Torino"));
	        teams.add(new Team("Udinese"));

	        Random rand = new Random();

	        // 2. Simulazione di tutte le partite (andata e ritorno)
	        for (int i = 0; i < teams.size(); i++) {
	            for (int j = i + 1; j < teams.size(); j++) {
	                Team home = teams.get(i);
	                Team away = teams.get(j);

	                // Simulazione andata
	                int homeGoals = rand.nextInt(4); // 0-3 gol
	                int awayGoals = rand.nextInt(4);
	                home.updateMatch(homeGoals, awayGoals);
	                away.updateMatch(awayGoals, homeGoals);

	                // Simulazione ritorno
	                int homeGoals2 = rand.nextInt(4);
	                int awayGoals2 = rand.nextInt(4);
	                away.updateMatch(homeGoals2, awayGoals2);
	                home.updateMatch(awayGoals2, homeGoals2);
	            }
	        }

	        // 3. Classifica finale (ordinata per punti)
	        teams.sort((a, b) -> b.points - a.points);

	        System.out.println("Classifica finale simulata:");
	        for (Team t : teams) {
	            System.out.println(t);
	        }
	    }
	}

