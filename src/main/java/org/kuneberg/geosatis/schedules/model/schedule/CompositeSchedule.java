package org.kuneberg.geosatis.schedules.model.schedule;

import java.util.List;

public class CompositeSchedule extends Schedule {

    private List<Schedule> including;
    private List<Schedule> excluding;

    public List<Schedule> getIncluding() {
        return including;
    }

    public CompositeSchedule setIncluding(List<Schedule> including) {
        this.including = including;
        return this;
    }

    public List<Schedule> getExcluding() {
        return excluding;
    }

    public CompositeSchedule setExcluding(List<Schedule> excluding) {
        this.excluding = excluding;
        return this;
    }
}
