package org.kuneberg.geosatis.schedules.model.schedule;

import java.util.Set;

public class PeriodicSchedule extends Schedule {
    private Scale scale;
    private int periodSize;
    private Set<Integer> periodSlots;

    public Scale getScale() {
        return scale;
    }

    public PeriodicSchedule setScale(Scale scale) {
        this.scale = scale;
        return this;
    }

    public int getPeriodSize() {
        return periodSize;
    }

    public PeriodicSchedule setPeriodSize(int periodSize) {
        this.periodSize = periodSize;
        return this;
    }

    public Set<Integer> getPeriodSlots() {
        return periodSlots;
    }

    public PeriodicSchedule setPeriodSlots(Set<Integer> periodSlots) {
        this.periodSlots = periodSlots;
        return this;
    }
}
