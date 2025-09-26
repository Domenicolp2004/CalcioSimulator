package simulatore;

import java.util.*;

public class SingleMatchSimulator {

    // Funzione Poisson già usata nel campionato
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

    // Funzione per simulare una singola partita
    public static Match playMatch(Team home, Team away, Random rand) {
        double expectedHome = (home.avgGoalsFor + away.avgGoalsAgainst) / 2.0;
        double expectedAway = (away.avgGoalsFor + home.avgGoalsAgainst) / 2.0;

        int homeGoals = poisson(expectedHome, rand);
        int awayGoals = poisson(expectedAway, rand);

        home.updateMatch(homeGoals, awayGoals);
        away.updateMatch(awayGoals, homeGoals);

        return new Match(home.name, away.name, homeGoals, awayGoals);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Lista squadre con medie
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Atalanta", 1.55, 1.10 ));
        teams.add(new Team("Bologna", 1.40, 1.18));
        teams.add(new Team("Cagliari", 1.10, 1.49));
        teams.add(new Team("Como", 1.37, 1.10));
        teams.add(new Team("Cremonese", 1.08, 1.30));
        teams.add(new Team("Fiorentina", 1.53, 1.19));
        teams.add(new Team("Genoa", 1.09, 1.15));
        teams.add(new Team("Hellas Verona", 0.92, 1.53));
        teams.add(new Team("Inter", 2.01, 0.99));
        teams.add(new Team("Juventus", 1.53, 0.93));
        teams.add(new Team("Lazio", 1.49, 1.05));
        teams.add(new Team("Lecce", 0.81, 1.42));
        teams.add(new Team("Milan", 1.76 , 0.95 ));
        teams.add(new Team("Napoli", 1.68, 0.85 ));
        teams.add(new Team("Parma", 1.00, 1.56 ));
        teams.add(new Team("Pisa", 0.80 , 1.50 ));
        teams.add(new Team("Roma", 1.58, 1.04 ));
        teams.add(new Team("Sassuolo",1.24 ,1.72 ));
        teams.add(new Team("Torino" ,1.08 ,1.20));
        teams.add(new Team("Udinese", 1.09, 1.35));


        System.out.println("Inserisci squadra di casa:");
        String homeName = scanner.nextLine();
        System.out.println("Inserisci squadra ospite:");
        String awayName = scanner.nextLine();

        Team home = null, away = null;
        for (Team t : teams) {
            if (t.name.equalsIgnoreCase(homeName)) home = t;
            if (t.name.equalsIgnoreCase(awayName)) away = t;
        }

        if (home != null && away != null) {
            Match risultato = playMatch(home, away, rand);
            System.out.println("Risultato simulato: " + risultato);
        } else {
            System.out.println("Una o entrambe le squadre non sono state trovate!");
        }
    }
}
