package simulatore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
	    String name;
	    double avgGoalsFor;     // media gol fatti a partita
	    double avgGoalsAgainst; 
	    int points = 0;
	    int goalsFor = 0;
	    int goalsAgainst = 0;
	    List<Player> players = new ArrayList<>();

	    Team(String name) {
	        this.name = name;
	    }

	    Team(String name, double avgGoalsFor, double avgGoalsAgainst) {
	        this.name = name;
	        this.avgGoalsFor = avgGoalsFor;
	        this.avgGoalsAgainst = avgGoalsAgainst;
	    }
	    
	    public void addPlayer(Player p) {
	        players.add(p);
	    } 
	    
	    public void scoreGoal(Random rand) {
	        if (!players.isEmpty()) {
	            Player scorer = players.get(rand.nextInt(players.size()));
	            scorer.score();
	            System.out.println("⚽ Gol di " + scorer.name + " per " + name);
	        }
	    }
	   
	    void updateMatch(int scored, int conceded) {
	        goalsFor += scored;
	        goalsAgainst += conceded;
	        if (scored > conceded) points += 3;
	        else if (scored == conceded) points += 1;
	        Random rand = new Random();
	        for (int i = 0; i < scored; i++) {
	            int index = rand.nextInt(players.size());
	            players.get(index).score();
	        }
	    
	    }


	    @Override
	    public String toString() {
	        return name + " | Punti: " + points + " | GF: " + goalsFor + " | GS: " + goalsAgainst;
	    }
	}
	
