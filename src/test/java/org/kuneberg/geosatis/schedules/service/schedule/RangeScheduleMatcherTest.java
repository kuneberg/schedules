package org.kuneberg.geosatis.schedules.service.schedule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kuneberg.geosatis.schedules.model.schedule.RangeSchedule;
import org.kuneberg.geosatis.schedules.model.schedule.Scale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RangeScheduleMatcherTest {


    @Test
    void testCase1() {
        var matcher = new RangeScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new RangeSchedule().setScale(Scale.YEAR)
                .setFrom(2021)
                .setTo(2023)
                .setNested(new RangeSchedule().setScale(Scale.MONTH).setFrom(11).setTo(11));

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertTrue(match);
    }

    @Test
    void testCase2() {
        var matcher = new RangeScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new RangeSchedule().setScale(Scale.YEAR)
                .setFrom(2020)
                .setTo(2021)
                .setNested(new RangeSchedule().setScale(Scale.MONTH).setFrom(11).setTo(11));

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertFalse(match);
    }

}
