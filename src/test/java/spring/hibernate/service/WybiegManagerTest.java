package spring.hibernate.service;
import spring.hibernate.domain.*;
import spring.hibernate.service.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by draxeer on 2016-12-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class WybiegManagerTest {

    @Autowired
    WybiegInterface test;
    private List<Wybieg> Sejf = new ArrayList<Wybieg>();

    @Before
    public void getWszystkieWybiegi() {
        List<Wybieg> wybieg = test.getAllWybiegi();
        for(Wybieg w : wybieg) {
            Sejf.add(w);
        }
    }

    @Test
    public void addWybieg() {
        Wybieg wybieg = new Wybieg("Test1", "232m2", "Safari");
        test.addWybieg(wybieg);
        Wybieg pobierz = test.getWybiegPoId(wybieg);
        assertEquals("Test1", pobierz.getNazwa_wybiegu());
        assertEquals("232m2", pobierz.getPowierzchnia());
        assertEquals("Safari", pobierz.getTyp_wybiegu());
    }

    @Test
    public void editWybieg() {

        Wybieg w = new Wybieg("Test2", "2212m2", "Akwariumm");
        test.addWybieg(w);

        Wybieg pobierz = test.getWybiegPoId(w);
        pobierz.setNazwa_wybiegu("Test2Edited");
        pobierz.setPowierzchnia("212m2");
        pobierz.setTyp_wybiegu("Akwarium");
        test.editWybieg(pobierz);

        Wybieg pobierzEdytowany = test.getWybiegPoId(pobierz);

        assertEquals("Test2Edited", pobierzEdytowany.getNazwa_wybiegu());
        assertEquals("212m2", pobierzEdytowany.getPowierzchnia());
        assertEquals("Akwarium", pobierzEdytowany.getTyp_wybiegu());

        test.deleteWybieg(pobierzEdytowany);
        List<Wybieg> nowyWybieg = test.getAllWybiegi();
        assertEquals(Sejf, nowyWybieg);
    }
    @Test
    public void deleteWybieg() { // Ew. dodać jakieś zwierze do wybiegu i usunąć je przed

        int ilosc = Sejf.size();
        Wybieg w = new Wybieg("Ruda", "121m2", "Terrarium");
        test.addWybieg(w);
        Wybieg pobierz = test.getWybiegPoId(w);
        test.deleteWybieg(pobierz);

        int iloscPoDel = test.getAllWybiegi().size();
        assertEquals(ilosc, iloscPoDel);
        assertNull(test.getWybiegPoId(w));
    }

    @Test
    public void getWybiegPoTyp() {
        int ilosc = test.getWybiegPoTyp("Terrarium2").size();
        Wybieg w = new Wybieg("Wonsze", "41m2", "Terrarium2");
        test.addWybieg(w);
        w = new Wybieg("Pajonk", "12m2", "Terrarium2");
        test.addWybieg(w);
        w = new Wybieg("Zdzislaf", "111m2", "Terrarium2");
        test.addWybieg(w);

        List<Wybieg> pobierz = test.getWybiegPoTyp("Terrarium2");

        for(Wybieg ww : pobierz) {
            assertEquals("Terrarium2", ww.getTyp_wybiegu());
        }
        assertEquals(ilosc+3, pobierz.size());
    }

    @After
    public void deleteWybiegiPoTestach() {

        List<Wybieg> baza = test.getAllWybiegi();
        boolean del = true;
        for (Wybieg w : baza) {
            for (Wybieg wyb : Sejf) {
                if (w.getWybieg_id() == wyb.getWybieg_id()) {
                    del = false;
                    break;
                }
            }
            if (del) {
                test.deleteWybieg(w);
            }
            del = true;
        }
    }
}