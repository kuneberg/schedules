package org.kuneberg.geosatis.schedules.service.schedule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kuneberg.geosatis.schedules.model.schedule.PeriodicSchedule;
import org.kuneberg.geosatis.schedules.model.schedule.RangeSchedule;
import org.kuneberg.geosatis.schedules.model.schedule.Scale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class PeriodicScheduleMatcherTest {


    @Test
    void testCase1() {
        var matcher = new PeriodicScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new PeriodicSchedule().setScale(Scale.YEAR)
                .setPeriodSize(2)
                .setPeriodSlots(Set.of(0))
                .setNested(new PeriodicSchedule().setScale(Scale.WEEK_DAY).setPeriodSize(7).setPeriodSlots(Set.of(0)));

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertTrue(match);
    }

    @Test
    void testCase2() {
        var matcher = new PeriodicScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new PeriodicSchedule().setScale(Scale.YEAR)
                .setPeriodSize(3)
                .setPeriodSlots(Set.of(1))
                .setNested(new PeriodicSchedule().setScale(Scale.WEEK_DAY).setPeriodSize(7).setPeriodSlots(Set.of(0)));

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertFalse(match);
    }

}
