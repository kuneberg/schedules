package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.EqualsSchedule;

public class EqualsScheduleMatcher extends AbstractScheduleMatcher<EqualsSchedule> {
    @Override
    public Class<EqualsSchedule> getAssignableFor() {
        return EqualsSchedule.class;
    }

    @Override
    public boolean match(MatchingContext<EqualsSchedule> ctx) {
        var valueMatched = matchValue(ctx);
        return valueMatched && super.match(ctx);
    }

    private boolean matchValue(MatchingContext<EqualsSchedule> ctx) {
        var schedule = ctx.schedule();
        var scale = schedule.getScale();
        var value = ctx.dateTime().get(scale.getScaleField());
        var scheduleValue = schedule.getValue();
        return value == scheduleValue;
    }
}
