package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        int euroa = 10/100;
        int senttia = 10%100;
        assertEquals("saldo: "+euroa+"."+senttia, kortti.toString());
    }
    
    @Test
    public void saldoKasvaaOikein() {
        kortti.lataaRahaa(10);
        int euroa = 20/100;
        int senttia = 20%100;
        assertEquals("saldo: "+euroa+"."+senttia, kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinKunRahaRiittaa() {
        boolean b = kortti.otaRahaa(5);
        assertEquals(true, b);
    }
    
    @Test
    public void saldoEiVaheneKunRahaEiRiita() {
        boolean b = kortti.otaRahaa(15);
        assertEquals(false, b);
    }
    
}
