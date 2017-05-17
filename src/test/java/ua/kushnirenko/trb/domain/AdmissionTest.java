package ua.kushnirenko.trb.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AdmissionTest {

    private Admission older = new Admission(LocalDateTime.of(2016, 1, 1, 0, 0, 0), 100L, 0.0f);
    private Admission younger = new Admission(LocalDateTime.now(), 200L, 0.0f);

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
}