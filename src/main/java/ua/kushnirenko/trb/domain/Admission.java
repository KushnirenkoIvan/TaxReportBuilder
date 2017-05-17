package ua.kushnirenko.trb.domain;

import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;


public class Admission {

    private LocalDateTime date;

    private long amount;

    private float ratio;

    private String desc;

    public LocalDateTime getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Admission(@NotNull LocalDateTime date) {
        this.date = date;
    }

    public Admission(@NotNull LocalDateTime date, long amount, float ratio, String desc) {
        this.date = date;
        this.amount = amount;
        this.ratio = ratio;
        this.desc = desc;
    }

    /**
     * Check, does current {@link Admission} is before or equal to the passed {@link LocalDateTime} value.
     *
     * @param t {@link LocalDateTime} is compared to the Admission's date.
     */
    public boolean isBe(LocalDateTime t) {
        return this.getDate().isBefore(t) || this.getDate().isEqual(t);
    }

    /**
     * Check, does current {@link Admission} is after or equal to the passed {@link LocalDateTime} value.
     *
     * @param t {@link LocalDateTime} is compared to the Admission's date.
     */
    public boolean isAe(LocalDateTime t) {
        return this.getDate().isAfter(t) || this.getDate().isEqual(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admission admission = (Admission) o;

        if (date != null ? !date.equals(admission.date) : admission.date != null) return false;
        if (amount != admission.amount) return false;
        if (Float.compare(admission.ratio, ratio) != 0) return false;
        return desc != null ? desc.equals(admission.desc) : admission.desc == null;
    }
}
