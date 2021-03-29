/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reetta
 */
public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    public MaksukorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {}
     
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         //String vastaus = kortti.toString();
         
         //assertEquals("Kortilla on rahaa 9.0 euroa", vastaus);
         assertEquals(maksukortinSaldo(10), kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVanhentaaSaldoaOikein() {
         
         kortti.syoEdullisesti();
         
         assertEquals(maksukortinSaldo(7.5), kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVanhentaaSaldoaOikein() {
         
         kortti.syoMaukkaasti();
         
         assertEquals(maksukortinSaldo(6), kortti.toString());
     }
     
     @Test
     public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
         
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         //saldo 2e
         kortti.syoEdullisesti();
         
         assertEquals(maksukortinSaldo(2), kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiEiViewSaldoaNegatiiviseksi() {
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         //saldo 2e
         kortti.syoMaukkaasti();
         
         assertEquals(maksukortinSaldo(2), kortti.toString());
     }
     
     @Test
     public void kortilleVoiLadataRahaa() {
         kortti.lataaRahaa(25);
         assertEquals(maksukortinSaldo(35), kortti.toString());
     }
     
     @Test
     public void kortinSaldoEiYlitaMaksimiarvoa() {
         kortti.lataaRahaa(200);
         assertEquals(maksukortinSaldo(150), kortti.toString());
     }
     
     @Test
     public void kortilleEiVoiLadataNegatiivista() {
         kortti.lataaRahaa(-5);
         assertEquals(maksukortinSaldo(10), kortti.toString());
     }
     
     @Test
     public void kortillaRahaaVainEdulliseenLounaaseen() {
         Maksukortti uusi = new Maksukortti(2.5);
         
         uusi.syoEdullisesti();
        
         assertEquals(maksukortinSaldo(0), uusi.toString());
     }
     
     @Test
     public void kortillaRahaaMaukkaaseenLounaaseen() {
         Maksukortti uusi = new Maksukortti(4);
         
         uusi.syoMaukkaasti();
         
         assertEquals(maksukortinSaldo(0), uusi.toString());
     }
     
     public String maksukortinSaldo(double saldo){
         return "Kortilla on rahaa " + saldo + " euroa";
     }
}
