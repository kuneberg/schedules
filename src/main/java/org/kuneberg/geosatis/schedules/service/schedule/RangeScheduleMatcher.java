package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.RangeSchedule;

public class RangeScheduleMatcher extends AbstractScheduleMatcher<RangeSchedule> {
    @Override
    public Class<RangeSchedule> getAssignableFor() {
        return RangeSchedule.class;
    }

    @Override
    public boolean match(MatchingContext<RangeSchedule> ctx) {
        var rangeMatched = matchRange(ctx);
        return rangeMatched && super.match(ctx);
    }

    private boolean matchRange(MatchingContext<RangeSchedule> ctx) {
        var schedule = ctx.schedule();
        var scale = schedule.getScale();
        var value = ctx.dateTime().get(scale.getScaleField());
        var from = schedule.getFrom();
        var to = schedule.getTo();
        return value >= from && value <= to;
    }
}
