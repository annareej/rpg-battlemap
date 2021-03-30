package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti rikas;
    Maksukortti koyha;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        rikas = new Maksukortti(500);
        koyha = new Maksukortti(100);
    }
    
    @Test
    public void konstruktoriAsettaaRahatOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void konstruktoriAsettaaMyydytMaukkaatLounaatOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void konstruktoriAsettaaMyydytEdullisetLounaatOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void onnistunutEdullisenLounaanMaksaminenPalauttaaVaihtorahat() {
        int vaihtoRahat = kassa.syoEdullisesti(400);
        assertEquals(160, vaihtoRahat);
    }
    
    @Test
    public void onnistunutEdullisenLounaanOstaminenKasvattaaKassaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void onnistunutEdullisenLounaanOstaminenKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());    
    }
    
    @Test
    public void epaonnistunutEdullisenLounaanMaksaminenPalauttaaMaksunKunRahaaEiTarpeeksi() {
        int vaihtoRahat = kassa.syoEdullisesti(200);
        assertEquals(200, vaihtoRahat);
    }
    
    @Test
    public void epäonnistunutEdullisenLounaanMaksaminenEiKasvataOstettujaLounaita() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void onnistunutMaukkaanLounaanOstaminenKasvattaaKassaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void onnistunutMaukkaanLounaanOstaminenKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        
    }
    
    @Test
    public void epaonnistunutMaukkaanLounaanMaksaminenPalauttaaMaksunKunRahaaEiTarpeeksi() {
        int vaihtoRahat = kassa.syoMaukkaasti(200);
        assertEquals(200, vaihtoRahat);
    }
    
    @Test
    public void epaonnistunutMaukkaanLounaanMaksaminenEiKasvataOstettujaLounaita() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaOnnistuuEdullisenLounaanMaksaminenKunSaldoaTarpeeksi() {
        assertEquals(true, kassa.syoEdullisesti(rikas));
    }
    
    @Test
    public void kortillaEpaonnistuuEdullisenLounaanMaksaminenKunSaldoaEiTarpeeksi() {
        assertEquals(false, kassa.syoEdullisesti(koyha));
    }
    
    @Test
    public void onnistuneenEdullisenLounaanKortillaMaksaminenKasvattaaMyyntia() {
        kassa.syoEdullisesti(rikas);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutEdullisenLounaanKortillaMaksaminenEiKasvataMyyntia() {
        kassa.syoEdullisesti(koyha);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaOnnistuuMaukkaanLounaanMaksaminenKunSaldoaTarpeeksi() {
        assertEquals(true, kassa.syoMaukkaasti(rikas));
    }
    
    @Test
    public void kortillaEpaonnistuuMaukkaanLounaanMaksaminenKunSaldoaEiTarpeeksi() {
        assertEquals(false, kassa.syoMaukkaasti(koyha));
    }
    
    @Test
    public void onnistuneenMaukkaanLounaanKortillaMaksaminenKasvattaaMyyntia() {
        kassa.syoMaukkaasti(rikas);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutMaukkaaLounaanKortillaMaksaminenEiKasvataMyyntia() {
        kassa.syoMaukkaasti(koyha);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void rahanLaittaminenKortilleKasvattaaKassaa() {
        kassa.lataaRahaaKortille(koyha, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivisenSummanLaittaminenKortilleEiKasvataKassaa() {
        kassa.lataaRahaaKortille(koyha, -500);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}
