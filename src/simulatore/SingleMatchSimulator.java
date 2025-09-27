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

        for (int i = 0; i < homeGoals; i++) {
            Player scorer = home.scoreGoal(rand);
            if (scorer != null)
                System.out.println("⚽ Gol di " + scorer.name + " (" + home.name + ")");
        }
        for (int i = 0; i < awayGoals; i++) {
            Player scorer = away.scoreGoal(rand);
            if (scorer != null)
                System.out.println("⚽ Gol di " + scorer.name + " (" + away.name + ")");
        }
        
        home.goalsFor += homeGoals;
        home.goalsAgainst += awayGoals;
        away.goalsFor += awayGoals;
        away.goalsAgainst += homeGoals;

        if (homeGoals > awayGoals) home.points += 3;
        else if (homeGoals == awayGoals) { home.points++; away.points++; }
        else away.points += 3;

        return new Match(home.name, away.name, homeGoals, awayGoals);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Lista squadre con medie
        List<Team> teams = new ArrayList<>();
        Team ata=(new Team("Atalanta", 1.55, 1.10 ));
        ata.addPlayer(new Player("Scamacca"));
        ata.addPlayer(new Player("Lookman"));
        teams.add(ata);
        Team bol=(new Team("Bologna", 1.40, 1.18));
        bol.addPlayer(new Player("Orsolini"));
        bol.addPlayer(new Player("Castro"));
        teams.add(bol);
        Team cag=(new Team("Cagliari", 1.10, 1.49));
        cag.addPlayer(new Player("Esposito"));
        cag.addPlayer(new Player("Belotti"));
        teams.add(cag);
        Team com=(new Team("Como", 1.37, 1.10));
        com.addPlayer(new Player("Nico Paz"));
        com.addPlayer(new Player("Morata"));
        teams.add(com);
        Team cre=(new Team("Cremonese", 1.08, 1.30));
        cre.addPlayer(new Player("Vardy"));
        cre.addPlayer(new Player("Vazquez"));
        teams.add(cre);
        Team fio=(new Team("Fiorentina", 1.53, 1.19));
        fio.addPlayer(new Player("Kean"));
        fio.addPlayer(new Player("Gudmundsson"));
        teams.add(fio);
        Team gen=(new Team("Genoa", 1.09, 1.15));
        gen.addPlayer(new Player("Colombo"));
        gen.addPlayer(new Player("Carboni"));
        teams.add(gen);
        Team hel=(new Team("Hellas Verona", 0.92, 1.53));
        hel.addPlayer(new Player("Gift-Orban"));
        hel.addPlayer(new Player("Giovane"));
        teams.add(hel);
        Team inter=(new Team("Inter", 2.01, 0.99));
        inter.addPlayer(new Player("Lautaro Martinez"));
        inter.addPlayer(new Player("Thuram"));
        teams.add(inter);
        Team juv=(new Team("Juventus", 1.53, 0.93));
        juv.addPlayer(new Player("David"));
        juv.addPlayer(new Player("Vlahovic"));
        teams.add(juv);
        Team laz=(new Team("Lazio", 1.49, 1.05));
        laz.addPlayer(new Player("Castellanos"));
        laz.addPlayer(new Player("Zaccagni"));
        teams.add(laz);
        Team lec=(new Team("Lecce", 0.81, 1.42));
        lec.addPlayer(new Player("Stulic"));
        lec.addPlayer(new Player("Sottil"));
        teams.add(lec);
        Team mil=(new Team("Milan", 1.76 , 0.95 ));
        mil.addPlayer(new Player("Leao"));
        mil.addPlayer(new Player("Pulisic"));
        teams.add(mil);
        Team nap=(new Team("Napoli", 1.68, 0.85 ));
        nap.addPlayer(new Player("Hojlund"));
        nap.addPlayer(new Player("McTominay"));
        teams.add(nap);
        Team par=(new Team("Parma", 1.00, 1.56 ));
        par.addPlayer(new Player("Pellegrino"));
        par.addPlayer(new Player("Bernabe"));
        teams.add(par);
        Team pis=(new Team("Pisa", 0.80 , 1.50 ));
        pis.addPlayer(new Player("Nzola"));
        pis.addPlayer(new Player("Tramoni"));
        teams.add(pis);
        Team rom=(new Team("Roma", 1.58, 1.04 ));
        rom.addPlayer(new Player("Ferguson"));
        rom.addPlayer(new Player("Dybala"));
        teams.add(rom);
        Team sas=(new Team("Sassuolo",1.24 ,1.72 ));
        sas.addPlayer(new Player("Berardi"));
        sas.addPlayer(new Player("Pinamonti"));
        teams.add(sas);
        Team tor=(new Team("Torino" ,1.08 ,1.20));
        tor.addPlayer(new Player("Simeone"));
        tor.addPlayer(new Player("Vlasic"));
        teams.add(tor);
        Team udi=(new Team("Udinese", 1.09, 1.35));
        udi.addPlayer(new Player("Davis"));
        udi.addPlayer(new Player("Atta"));
        teams.add(udi);


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
