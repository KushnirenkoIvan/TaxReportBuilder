package ua.kushnirenko.trb.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AdmissionTest {

    private Admission older = new Admission(LocalDateTime.of(2016, 1, 1, 0, 0, 0));
    private Admission younger = new Admission(LocalDateTime.now());

    private Admission forComparison = new Admission(LocalDateTime.of(2016, 1, 1, 0, 0, 0));

    @Test
    public void isBe_BeforeTest() {
        assertTrue(older.isBe(younger.getDate()));
    }

    @Test
    public void isBe_AfterTest() {
        assertFalse(younger.isBe(older.getDate()));
    }

    @Test
    public void isBe_EqualTest() {
        assertTrue(older.isBe(older.getDate()));
    }

    @Test
    public void isAe_AfterTest() {
        assertTrue(younger.isAe(older.getDate()));
    }

    @Test
    public void isAe_BeforeTest() {
        assertFalse(older.isAe(younger.getDate()));
    }

    @Test
    public void isAe_EqualTest() {
        assertTrue(younger.isAe(younger.getDate()));
    }

    @Test
    public void equals_OnlyDateSpecifiedTest() {
        assertTrue(older.equals(forComparison));
        assertFalse(younger.equals(older));
    }

    @Test
    public void equals_AmountFieldTest() {
        older.setAmount(40000L);
        assertFalse(older.equals(forComparison));

        forComparison.setAmount(40000L);
        assertTrue(older.equals(forComparison));
    }

    @Test
    public void equals_RatioFieldTest() {
        older.setRatio(4.0f);
        assertFalse(older.equals(forComparison));

        forComparison.setRatio(4.0f);
        assertTrue(older.equals(forComparison));
    }

    @Test
    public void equals_DescFieldTest() {
        older.setDesc("desc");
        assertFalse(older.equals(forComparison));

        forComparison.setDesc("desc");
        assertTrue(older.equals(forComparison));
    }

    @Test
    public void equals_SameElemsTest() {
        older.setAmount(40000L);
        older.setRatio(4.0f);
        older.setDesc("desc");

        forComparison.setAmount(40000L);
        forComparison.setRatio(4.0f);
        forComparison.setDesc("desc");

        assertTrue(older.equals(forComparison));
    }
}