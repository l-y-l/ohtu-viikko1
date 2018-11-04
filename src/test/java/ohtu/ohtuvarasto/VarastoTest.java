package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    //Konstruktori tilavuudella
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNegatiivisenVaraston() {
        varasto = new Varasto(-1);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVirheellisenVaraston() {
        varasto = new Varasto(Double.NaN);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    //Konstruktori tilavuus + saldo
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaSaldo() {
        varasto = new Varasto(10, 5);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    
    @Test
    public void konstruktoriSaldoaEnemmanKuinTilavuutta() {
        varasto = new Varasto(2, 5);
        
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNollaTilavuus() {
        varasto = new Varasto(0, 5);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNegatiivinenTilavuus() {
        varasto = new Varasto(-1, 5);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNegatiivinenSaldo() {
        varasto = new Varasto(10, -1);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    //Paljonko Mahtuu
    @Test
    public void tilaaOikeaMaara() {
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    //LisaaVarastoon()
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysNegatiivinenMaara() {
        varasto.lisaaVarastoon(-1);

        // saldon pitäisi olla muuttumaton eli 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysYlitayttaaVaraston() {
        varasto.lisaaVarastoon(12);

        // saldon pitäisi olla tilavuuden verran eli 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    //otaVarastosta()
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivinenMaara() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-2);

        // saldon pitäisi olla sama kun lisätty määrä eli 8 ja otettua 0
        assertEquals(0, saatuMaara, vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEnemmanKuinSaldoa() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(10);

        // nosto on koko alkuperainen saldo, ja varaston pitaa olla tyhja
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEnemmanKuinTilavuutta() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(12);

        // nosto on koko alkuperainen saldo, ja varaston pitaa olla tyhja
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    //toString()
    @Test
    public void oikeaTeksti() {
        varasto.lisaaVarastoon(8);
        
        String vastaus = varasto.toString();

        assertEquals("saldo = 8.0, vielä tilaa 2.0", vastaus);
    }
}