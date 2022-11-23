package org.kuneberg.geosatis.schedules.model.schedule;

public class RangeSchedule extends Schedule {
    private Scale scale;
    private int from;
    private int to;

    public Scale getScale() {
        return scale;
    }

    public RangeSchedule setScale(Scale scale) {
        this.scale = scale;
        return this;
    }

    public int getFrom() {
        return from;
    }

    public RangeSchedule setFrom(int from) {
        this.from = from;
        return this;
    }

    public int getTo() {
        return to;
    }

    public RangeSchedule setTo(int to) {
        this.to = to;
        return this;
    }
}
