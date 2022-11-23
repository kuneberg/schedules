package org.kuneberg.geosatis.schedules.model.schedule;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EqualsSchedule.class, name = "eq"),
        @JsonSubTypes.Type(value = RangeSchedule.class, name = "range"),
        @JsonSubTypes.Type(value = PeriodicSchedule.class, name = "period"),
        @JsonSubTypes.Type(value = CompositeSchedule.class, name = "composite")
})
public abstract class Schedule {

    private Schedule nested;

    public Schedule getNested() {
        return nested;
    }

    public Schedule setNested(Schedule nested) {
        this.nested = nested;
        return this;
    }
}
