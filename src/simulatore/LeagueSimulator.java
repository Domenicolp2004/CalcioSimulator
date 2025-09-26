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
	        // 1. Lista squadre (caricate prendendo spunto da dati FootyStats)
	        List<Team> teams = new ArrayList<>();
	        teams.add(new Team("Atalanta", 1.55, 1.10 ));
	        teams.add(new Team("Bologna", 1.40, 1.18));
	        teams.add(new Team("Cagliari", 1.10, 1.49));
	        teams.add(new Team("Como", 1.37, 1.10));
	        teams.add(new Team("Cremonese", 1.15, 1.30));
	        teams.add(new Team("Fiorentina", 1.53, 1.19));
	        teams.add(new Team("Genoa", 1.09, 1.15));
	        teams.add(new Team("Hellas Verona", 0.92, 1.53));
	        teams.add(new Team("Inter", 2.01, 0.99));
	        teams.add(new Team("Juventus", 1.53, 0.93));
	        teams.add(new Team("Lazio", 1.49, 1.05));
	        teams.add(new Team("Lecce", 0.81, 1.42));
	        teams.add(new Team("Milan", 1.76 , 0.95 ));
	        teams.add(new Team("Napoli", 1.67, 0.90 ));
	        teams.add(new Team("Parma", 1.00, 1.56 ));
	        teams.add(new Team("Pisa", 0.80 , 1.50 ));
	        teams.add(new Team("Roma", 1.58, 1.04 ));
	        teams.add(new Team("Sassuolo",1.24 ,1.72 ));
	        teams.add(new Team("Torino" ,1.08 ,1.20));
	        teams.add(new Team("Udinese", 1.09, 1.35));

	        Random rand = new Random();

	        // 2. Simulazione di tutte le partite (andata e ritorno)
	        for (int i = 0; i < teams.size(); i++) {
	            for (int j = i + 1; j < teams.size(); j++) {
	                Team home = teams.get(i);
	                Team away = teams.get(j);


	             // Partita di andata
	                double expectedHome = (home.avgGoalsFor + away.avgGoalsAgainst) / 2.0;
	                double expectedAway = (away.avgGoalsFor + home.avgGoalsAgainst) / 2.0;

	                int homeGoals = poisson(expectedHome, rand);
	                int awayGoals = poisson(expectedAway, rand);

	                home.updateMatch(homeGoals, awayGoals);
	                away.updateMatch(awayGoals, homeGoals);

	                // Partita di ritorno
	                double expectedHome2 = (away.avgGoalsFor + home.avgGoalsAgainst) / 2.0;
	                double expectedAway2 = (home.avgGoalsFor + away.avgGoalsAgainst) / 2.0;

	                int homeGoals2 = poisson(expectedHome2, rand);
	                int awayGoals2 = poisson(expectedAway2, rand);

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

