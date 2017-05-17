package ua.kushnirenko.trb.domain;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * Represents history of the admissions.
 */
public class History implements Serializable {

    private String name;
    private String pwd;
    private String email;

    private TreeSet<Admission> admissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TreeSet<Admission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(TreeSet<Admission> admissions) {
        this.admissions = admissions;
    }

    public History(String name, String pwd, String email) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;

        admissions = new TreeSet<>((a1, a2) -> {
            if (a1.getDate().isEqual(a2.getDate())) return 0;
            if (a1.getDate().isAfter(a2.getDate())) return 1;
            else return -1;
        });
    }

    public boolean addAdmission(@NotNull Admission a) {
        return admissions.add(a);
    }

    public Admission getAdmissionByDate(@NotNull LocalDateTime t) {
        Admission adm = admissions.ceiling(new Admission(t));
        if (adm != null && adm.getDate().isEqual(t)) return adm;
        return null;
    }

    public List<Admission> getAdmissionsInTimeRange(@NotNull LocalDateTime t1, @NotNull LocalDateTime t2) {
        if (t1.isAfter(t2)) throw new IllegalArgumentException("Left time bound cannot be after right time bound!");
        if (admissions.isEmpty()
                || admissions.first().getDate().isAfter(t2)
                || admissions.last().getDate().isBefore(t1)) return null;

        Admission curr = admissions.ceiling(new Admission(t1));

        List<Admission> result = new ArrayList<>();
        while (curr != null && curr.isBe(t2)) {
            result.add(curr);
            curr = admissions.higher(curr);
        }
        return result;
    }

    public boolean updateAdmission(@NotNull Admission a) {
        Admission adm = this.getAdmissionByDate(a.getDate());
        if (adm != null) {
            adm.setAmount(a.getAmount());
            adm.setRatio(a.getRatio());
            adm.setDesc(a.getDesc());
            return true;
        }
        return false;
    }

    public Admission deleteAdmission(@NotNull LocalDateTime date) {
        Admission adm = this.getAdmissionByDate(date);

        if (adm != null) admissions.remove(adm);

        return adm;
    }

    public int amountOfAdmissions() {
        return this.getAdmissions().size();
    }
}