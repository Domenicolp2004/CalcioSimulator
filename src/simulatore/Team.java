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
	    
	    public Team(String name) {
	        this.name = name;
	    }


	   public Team(String name, double avgGoalsFor, double avgGoalsAgainst) {
	        this.name = name;
	        this.avgGoalsFor = avgGoalsFor;
	        this.avgGoalsAgainst = avgGoalsAgainst;
	    }
	    
	    public void addPlayer(Player p) {
	        players.add(p);
	    } 
	    
	    public Player scoreGoal(Random rand) {
	        if (!players.isEmpty()) {
	            Player scorer = players.get(rand.nextInt(players.size()));
	            scorer.score();
	            return scorer;
	        }
	        return null;
	    }


	    @Override
	    public String toString() {
	        return name + " | Punti: " + points + " | GF: " + goalsFor + " | GS: " + goalsAgainst;
	    }
	}
	
