package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.PeriodicSchedule;

public class PeriodicScheduleMatcher extends AbstractScheduleMatcher<PeriodicSchedule> {
    @Override
    public Class<PeriodicSchedule> getAssignableFor() {
        return PeriodicSchedule.class;
    }

    @Override
    public boolean match(MatchingContext<PeriodicSchedule> ctx) {
        var matchPeriod = matchPeriod(ctx);
        return matchPeriod && super.match(ctx);
    }

    private boolean matchPeriod(MatchingContext<PeriodicSchedule> ctx) {
        var schedule = ctx.schedule();

        var scaleField = schedule.getScale().getScaleField();

        var dateValue = ctx.dateTime().get(scaleField);
        if (scaleField.range().getMinimum() == 1) {
            dateValue -= 1;
        }
        var periodicValue = dateValue % schedule.getPeriodSize();
        return schedule.getPeriodSlots().contains(periodicValue);
    }
}
