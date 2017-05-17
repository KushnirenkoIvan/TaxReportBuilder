package ua.kushnirenko.trb.domain;

import java.time.LocalDateTime;


public class Admission {

    private LocalDateTime date;

    private long amount;

    private float ratio;

    private String desc;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public Admission() {
    }

    public Admission(LocalDateTime date) {
        this.date = date;
    }

    public Admission(LocalDateTime date, long amount, float ratio) {
        this.date = date;
        this.amount = amount;
        this.ratio = ratio;
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
    public boolean equals(Object obj) {
        Admission adm = (Admission) obj;

        return this.getDate().isEqual(adm.getDate())
                && this.getAmount() == adm.getAmount()
                && this.getRatio() == adm.getRatio()
                && this.getDesc().equals(adm.getDesc());
    }
}
