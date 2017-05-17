package ua.kushnirenko.trb.domain;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;


public class HistoryTest {

    private History hst = new History("", "", "");
    private History emptyHst = new History("", "", "");

    private Admission adm1 = new Admission(LocalDateTime.of(2016, 6, 1, 0, 0),
            73600L,
            8.54f,
            "The stipend from the university.");
    private Admission adm2 = new Admission(LocalDateTime.of(2016, 7, 31, 23, 59),
            500000L,
            27.32f,
            "My first salary!");
    private Admission adm3 = new Admission(LocalDateTime.of(2016, 8, 12, 0, 0),
            1200000L,
            26.42f,
            "My current month salary.");

    {
        hst.addAdmission(adm1);
        hst.addAdmission(adm2);
        hst.addAdmission(adm3);
    }

    @Test
    public void addAdmission_NewElemTest() {
        assertTrue(emptyHst.addAdmission(adm1));
        assertEquals(1, emptyHst.getAdmissions().size());

        emptyHst.setAdmissions(new TreeSet<>((a1, a2) -> {
            if (a1.getDate().isEqual(a2.getDate())) return 0;
            if (a1.getDate().isAfter(a2.getDate())) return 1;
            else return -1;
        }));
    }

    @Test
    public void addAdmission_PresentElemTest() {
        assertFalse(hst.addAdmission(adm1));
        assertEquals(3, hst.getAdmissions().size());
    }

    @Test
    public void getAdmissionByDate_MissingElemTest() {
        assertNull(emptyHst.getAdmissionByDate(adm1.getDate()));
    }

    @Test
    public void getAdmissionByDate_PresentElemTest() {
        assertEquals(adm2, hst.getAdmissionByDate(LocalDateTime.of(2016, 7, 31, 23, 59)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInTimeRange_InvalidRangeTest() {
        hst.getAdmissionsInTimeRange(LocalDateTime.MAX, LocalDateTime.MIN);
    }

    @Test
    public void getInTimeRange_EarlierRangeTest() {
        assertNull(
                hst.getAdmissionsInTimeRange(LocalDateTime.of(2014, 1, 1, 0, 0), LocalDateTime.of(2015, 1, 1, 0, 0))
        );
    }

    @Test
    public void getInTimeRange_ElderRangeTest() {
        assertNull(
                hst.getAdmissionsInTimeRange(LocalDateTime.of(2017, 1, 1, 0, 0), LocalDateTime.of(2017, 2, 1, 0, 0))
        );
    }

    @Test
    public void getInTimeRange_LeftBoundTest() {
        List<Admission> adm_lst = hst.getAdmissionsInTimeRange(LocalDateTime.of(2016, 6, 1, 0, 0), LocalDateTime.of(2016, 6, 1, 0, 0));

        assertTrue(adm_lst.size() == 1);
        assertTrue(adm_lst.get(0).equals(adm1));
    }

    @Test
    public void getInTimeRange_RightBoundTest() {
        List<Admission> adm_lst
                = hst.getAdmissionsInTimeRange(LocalDateTime.of(2016, 8, 12, 0, 0), LocalDateTime.of(2016, 9, 1, 0, 0));

        assertTrue(adm_lst.size() == 1);
        assertTrue(adm_lst.get(0).equals(adm3));
    }

    @Test
    public void getInTimeRange_StrictBoundsTest() {
        List<Admission> adm_lst
                = hst.getAdmissionsInTimeRange(LocalDateTime.of(2016, 6, 1, 0, 0), LocalDateTime.of(2016, 8, 12, 0, 0));

        assertTrue(adm_lst.size() == 3);
    }

    @Test
    public void getInTimeRange_UnStrictBoundsTest() {
        List<Admission> adm_lst
                = hst.getAdmissionsInTimeRange(LocalDateTime.of(2016, 6, 1, 0, 1), LocalDateTime.of(2016, 8, 11, 23, 59));

        assertTrue(adm_lst.size() == 1);
        assertTrue(adm_lst.get(0).equals(adm2));
    }

    @Test
    public void updateAdmission_MissingElemTest() {
        assertFalse(emptyHst.updateAdmission(adm1));
    }

    @Test
    public void updateAdmission_PresentElemTest() {
        adm1.setAmount(10000000000L);
        adm1.setRatio(26.38f);
        adm1.setDesc("I've won the lottery!");

        assertTrue(hst.updateAdmission(adm1));
        assertEquals(adm1, hst.getAdmissionByDate(adm1.getDate()));
    }

    @Test
    public void deleteAdmission_MissingElemTest() {
        assertNull(emptyHst.deleteAdmission(adm1.getDate()));
    }

    @Test
    public void deleteAdmission_PresentElemTest() {
        TreeSet<Admission> admissions = new TreeSet<>((a1, a2) -> {
            if (a1.getDate().isEqual(a2.getDate())) return 0;
            if (a1.getDate().isAfter(a2.getDate())) return 1;
            else return -1;
        });
        admissions.add(adm1);

        emptyHst.setAdmissions(admissions);

        assertEquals(adm1, emptyHst.deleteAdmission(adm1.getDate()));
        assertEquals(0, emptyHst.getAdmissions().size());
    }

    @Test
    public void amountOfAdmissions_EmptyHistoryTest() {
        assertEquals(0, emptyHst.amountOfAdmissions());
    }

    @Test
    public void amountOfAdmissions_FilledHistoryTest() {
        assertEquals(3, hst.amountOfAdmissions());
    }
}