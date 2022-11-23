package org.kuneberg.geosatis.schedules.service.schedule;


import org.kuneberg.geosatis.schedules.model.schedule.Schedule;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScheduleMatchersRegistry {

    private final Map<Class<? extends Schedule>, ScheduleMatcher> scheduleMatchers;

    public ScheduleMatchersRegistry(List<ScheduleMatcher> scheduleMatchers) {
        this.scheduleMatchers = scheduleMatchers.stream()
                .collect(Collectors.toMap(ScheduleMatcher::getAssignableFor, Function.identity(), (v1, v2) -> v2));
    }

    public ScheduleMatchersRegistry() {
        this(List.of(new EqualsScheduleMatcher(), new RangeScheduleMatcher(), new PeriodicScheduleMatcher(), new CompositeScheduleMatcher()));
    }

    public ScheduleMatcher get(Schedule schedule) {
        return get(schedule.getClass());
    }

    public ScheduleMatcher get(Class<? extends Schedule> scheduleClass) {
        return scheduleMatchers.get(scheduleClass);
    }
}
