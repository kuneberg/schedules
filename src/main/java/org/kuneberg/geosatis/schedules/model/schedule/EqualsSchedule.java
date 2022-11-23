package org.kuneberg.geosatis.schedules.model.schedule;


public class EqualsSchedule extends Schedule {
    private Scale scale;
    private int value;

    public Scale getScale() {
        return scale;
    }

    public EqualsSchedule setScale(Scale scale) {
        this.scale = scale;
        return this;
    }

    public int getValue() {
        return value;
    }

    public EqualsSchedule setValue(int value) {
        this.value = value;
        return this;
    }
}
