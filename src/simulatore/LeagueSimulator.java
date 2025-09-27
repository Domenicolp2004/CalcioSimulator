package simulatore;

import java.util.*;
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

	        Random rand = new Random();
	        
	      List<Match> matches = new ArrayList<>();

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

	                for (int g = 0; g < homeGoals; g++) home.scoreGoal(rand);
	                for (int g = 0; g < awayGoals; g++) away.scoreGoal(rand);

	                home.goalsFor += homeGoals;
	                home.goalsAgainst += awayGoals;
	                away.goalsFor += awayGoals;
	                away.goalsAgainst += homeGoals;

	                if (homeGoals > awayGoals) home.points += 3;
	                else if (homeGoals == awayGoals) { home.points++; away.points++; }
	                else away.points += 3;

	                matches.add(new Match(home.name, away.name, homeGoals, awayGoals));
	                
	                // Partita di ritorno
	                double expectedHome2 = (away.avgGoalsFor + home.avgGoalsAgainst) / 2.0;
	                double expectedAway2 = (home.avgGoalsFor + away.avgGoalsAgainst) / 2.0;

	                int homeGoals2 = poisson(expectedHome2, rand);
	                int awayGoals2 = poisson(expectedAway2, rand);

	                for (int g = 0; g < homeGoals2; g++) away.scoreGoal(rand);
	                for (int g = 0; g < awayGoals2; g++) home.scoreGoal(rand);

	                away.goalsFor += homeGoals2;
	                away.goalsAgainst += awayGoals2;
	                home.goalsFor += awayGoals2;
	                home.goalsAgainst += homeGoals2;

	                if (homeGoals2 > awayGoals2) away.points += 3;
	                else if (homeGoals2 == awayGoals2) { home.points++; away.points++; }
	                else home.points += 3;

	                matches.add(new Match(away.name, home.name, homeGoals2, awayGoals2));
	            }
	           }
	       

	        // 3. Classifica finale (ordinata per punti)
	        teams.sort((a, b) -> b.points - a.points);

	        System.out.println("Classifica finale simulata:");
	        for (Team t : teams) {
	            System.out.println(t);
	        }
	        
	        System.out.println("\nTutti i risultati:");
	        for (Match m : matches) {
	            System.out.println(m);
	        }
	        
	        System.out.println("\n⚽ Marcatori:");
	        for (Team t : teams) {
	            for (Player p : t.players) {
	                if (p.goals > 0) {
	                    System.out.println(p + " - " + t.name);
	                }
	            }
	        }
	    
	    }
	    
	    
	}

