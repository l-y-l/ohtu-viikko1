
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    //Player search(String name)
    @Test
    public void pelaajaPuuttuu() {
        assertEquals(null, stats.search("nobody"));
    }
    
    @Test
    public void pelaajaEnsimmainen() {
        Player vastaus = new Player("Semenko", "EDM", 4, 12);
        
        assertEquals(vastaus.toString(), stats.search("Semenko").toString());
    }
    
    @Test
    public void pelaajaKeskelta() {
        Player vastaus = new Player("Kurri",   "EDM", 37, 53);
        
        assertEquals(vastaus.toString(), stats.search("Kurri").toString());
    }
    
    @Test
    public void pelaajaViimeinen() {
        Player vastaus = new Player("Gretzky", "EDM", 35, 89);
        
        assertEquals(vastaus.toString(), stats.search("Gretzky").toString());
    }
    
    //List<Player> team(String teamName)
    @Test
    public void joukkuePuuttuu() {
        assertEquals(new ArrayList<Player>(), stats.team("ABC"));
    }
    
    @Test
    public void joukkueYksiPelaajaa() {
        ArrayList<Player> vastaus = new ArrayList<Player>();

        vastaus.add(new Player("Lemieux", "PIT", 45, 54));
        
        assertEquals(vastaus.toString(), stats.team("PIT").toString());
    }
    
    @Test
    public void joukkueMontaPelaajaa() {
        ArrayList<Player> vastaus = new ArrayList<Player>();

        vastaus.add(new Player("Semenko", "EDM", 4, 12));
        vastaus.add(new Player("Kurri",   "EDM", 37, 53));
        vastaus.add(new Player("Gretzky", "EDM", 35, 89));
        
        assertEquals(vastaus.toString(), stats.team("EDM").toString());
    }
    
    //List<Player> topScorers(int howMany)
    @Test
    public void pisteKarkiaNegatiivinenMaara() {
        assertEquals(new ArrayList<Player>(), stats.topScorers(-1));
    }
    
    @Test
    public void pisteKarkiaNolla() {
        assertEquals(new ArrayList<Player>(), stats.topScorers(0));
    }
    
    @Test
    public void pisteKarkiaYksi() {
        ArrayList<Player> vastaus = new ArrayList<Player>();
        
        vastaus.add(new Player("Gretzky", "EDM", 35, 89));
        
        assertEquals(vastaus.toString(), stats.topScorers(1).toString());
    }
    
    @Test
    public void pisteKarkiaKaikki() {
        ArrayList<Player> vastaus = new ArrayList<Player>(readerStub.getPlayers());
        
        Collections.sort(vastaus);
        
        assertEquals(vastaus.toString(), stats.topScorers(5).toString());
    }
    
    /*
    @Test
    public void pisteKarkiaLiikaa() {
        ArrayList<Player> vastaus = new ArrayList<Player>(readerStub.getPlayers());
        
        Collections.sort(vastaus);
        
        assertEquals(vastaus.toString(), stats.topScorers(15).toString());
    }
    */
}
