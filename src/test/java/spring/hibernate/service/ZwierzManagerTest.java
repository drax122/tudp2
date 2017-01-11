package spring.hibernate.service;
import spring.hibernate.domain.*;
import spring.hibernate.service.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
 * Created by draxeer on 2016-12-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ZwierzManagerTest {
    @Autowired
    ZwierzInterface zwTest;
    @Autowired
    WybiegInterface wyTest;

    private List<Wybieg> Sejf = new ArrayList<Wybieg>();
    private List<Zwierz> Sejf2 = new ArrayList<Zwierz>();

    @Before
    public void getWszystkieWybiegi() {
        List<Wybieg> wybiegi = wyTest.getAllWybiegi();
        List<Zwierz> zwierzeta = zwTest.getAllZwierze();
        for(Zwierz zw : zwierzeta){
            Sejf2.add(zw);
        }
        for(Wybieg w : wybiegi) {
            Sejf.add(w);
        }
    }

    @Test
    public void addZwierze() {

        Wybieg w = new Wybieg("Elo","213m2","Safari");
        wyTest.addWybieg(w);
        assertNotNull(w);

        Zwierz z = new Zwierz("Kotowate","Lew","Miecho", w);
        zwTest.addZwierz(z);
        assertNotNull(z);

        Zwierz test = zwTest.getZwierzePoId(z);

        assertEquals(test.getRasa(), z.getRasa());
        assertEquals(test.getGatunek(), z.getGatunek());
        assertEquals(test.getCo_szama(), z.getCo_szama());
        assertEquals(test.getWybieg(), w);
        assertEquals(test.getWybieg().getNazwa_wybiegu(), w.getNazwa_wybiegu());
    }

    @Test
    public void getZwierzPoId() {

        Wybieg w = new Wybieg("Elo","213m2","Safari");
        wyTest.addWybieg(w);
        Zwierz z = new Zwierz("Kotowate","Lew","Miecho", w);
        zwTest.addZwierz(z);

        Zwierz zw = zwTest.getAllZwierze().get(0);
        Zwierz test = zwTest.getZwierzePoId(zw);

        assertEquals(zw, test);
        assertEquals(zw.getZwierz_ID(), test.getZwierz_ID());
    }

    @Test
    public void deleteZwierz() {
        Wybieg w = new Wybieg("Elo","213m2","Safari");
        wyTest.addWybieg(w);
        Zwierz z = new Zwierz("KrulKappa","Lew","Miecho", w);
        zwTest.addZwierz(z);

        assertEquals(zwTest.getZwierzePoId(z), z);
        zwTest.deleteZwierz(z);
        assertNull(zwTest.getZwierzePoId(z));
    }

    @Test
    public void getZwierzetaZWybiegu() {
        Wybieg w = new Wybieg("Elo320","213m2","Safari");
        wyTest.addWybieg(w);

        Zwierz z = new Zwierz("Kotowate","Lew","Miecho", w);
        zwTest.addZwierz(z);
        Zwierz z1 = new Zwierz("Kotowate","Tygrys","Miecho", w);
        zwTest.addZwierz(z1);

        List<Zwierz> zwierzetaZWybiegu = zwTest.getZwierzZWybiegu(w);

        assertEquals(zwierzetaZWybiegu.size(), 2);
        assertEquals(zwierzetaZWybiegu.get(0).getWybieg(), w);
        assertEquals(zwierzetaZWybiegu.get(1).getWybieg(), w);
    }

    @Test
    public void editZwierz() {
        Wybieg w = new Wybieg("Elo320","213m2","Safari");
        wyTest.addWybieg(w);
        Wybieg w1 = new Wybieg("Elo3201","2113m2","Sawanna");
        wyTest.addWybieg(w1);
        Zwierz z = new Zwierz("Kotowate","Lew","Miecho", w);
        zwTest.addZwierz(z);

        assertEquals(zwTest.getZwierzePoId(z).getWybieg().getNazwa_wybiegu(), w.getNazwa_wybiegu());
        z.setWybieg(w1);
        zwTest.editZwierz(z);
        assertEquals(zwTest.getZwierzePoId(z).getWybieg().getNazwa_wybiegu(), w1.getNazwa_wybiegu());
    }

    @After
    public void deletePrawieALLPoTestach() {

        List<Wybieg> baza = wyTest.getAllWybiegi();
        List<Zwierz> baza2 = zwTest.getAllZwierze();
        boolean del = true;

        for (Zwierz zw : baza2) {
            for (Zwierz zw1 : Sejf2) {
                if (zw.getZwierz_ID() == zw1.getZwierz_ID()) {
                    del = false;
                    break;
                }
            }
            if (del) {
                zwTest.deleteZwierz(zw);
            }
            del = true;
        }
        del = true;
        for (Wybieg w : baza) {
            for (Wybieg wyb : Sejf) {
                if (w.getWybieg_id() == wyb.getWybieg_id()) {
                    del = false;
                    break;
                }
            }
            if (del) {
                wyTest.deleteWybieg(w);
            }
            del = true;
        }
    }
}