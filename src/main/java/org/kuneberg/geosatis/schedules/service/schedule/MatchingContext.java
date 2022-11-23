package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.Schedule;

import java.time.LocalDateTime;

public record MatchingContext<T extends Schedule> (ScheduleMatchersRegistry registry, T schedule, LocalDateTime dateTime) {

    public <M extends Schedule> MatchingContext<M> createFor(M schedule) {
        return new MatchingContext(registry(), schedule, dateTime);
    }

    public <M extends Schedule> ScheduleMatcher<M> getMatcher(M schedule) {
        return registry.get(schedule);
    }
}
