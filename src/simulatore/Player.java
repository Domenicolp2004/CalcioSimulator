package simulatore;

public class Player {
    String name;
    int goals = 0;

    public Player(String name) 
    { 
    	this.name = name;
    	this.goals = 0;
    	}

    public void score() {
    	goals++; 
    	}

    @Override
    public String toString() { return name + " (" + goals + " gol)"; }
}