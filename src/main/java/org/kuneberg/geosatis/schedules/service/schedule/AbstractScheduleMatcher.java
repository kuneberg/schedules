package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.Schedule;

import java.util.Optional;

public abstract class AbstractScheduleMatcher<T extends Schedule> implements ScheduleMatcher<T>  {

    @Override
    public boolean match(MatchingContext<T> ctx) {
        var nested = ctx.schedule().getNested();
        return Optional.ofNullable(nested)
                .map(n -> ctx.getMatcher(n).match(ctx.createFor(n)))
                .orElse(true);
    }
}
