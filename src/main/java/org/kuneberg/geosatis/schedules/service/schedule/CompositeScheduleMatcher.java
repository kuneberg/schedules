package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.CompositeSchedule;

public class CompositeScheduleMatcher implements ScheduleMatcher<CompositeSchedule> {


    @Override
    public Class<CompositeSchedule> getAssignableFor() {
        return CompositeSchedule.class;
    }

    @Override
    public boolean match(MatchingContext<CompositeSchedule> ctx) {
        var schedule = ctx.schedule();
        var isExcluded = schedule
                .getExcluding()
                .stream()
                .anyMatch(s -> ctx.getMatcher(s).match(ctx.createFor(s)));
        if (isExcluded) {
            return false;
        }
        return schedule
                .getIncluding()
                .stream()
                .anyMatch(s -> ctx.getMatcher(s).match(ctx.createFor(s)));
    }
}
