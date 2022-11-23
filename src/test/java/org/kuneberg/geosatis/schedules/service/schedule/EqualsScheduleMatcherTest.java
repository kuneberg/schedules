package org.kuneberg.geosatis.schedules.service.schedule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kuneberg.geosatis.schedules.model.schedule.EqualsSchedule;
import org.kuneberg.geosatis.schedules.model.schedule.Scale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EqualsScheduleMatcherTest {


    @Test
    void testCase1() {
        var matcher = new EqualsScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new EqualsSchedule().setScale(Scale.YEAR)
                .setValue(2022)
                .setNested(new EqualsSchedule().setScale(Scale.MONTH).setValue(11));

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertTrue(match);
    }

    @Test
    void testCase2() {
        var matcher = new EqualsScheduleMatcher();
        var dateTime = LocalDateTime.parse("2022-11-21T11:56:02.528649", DateTimeFormatter.ISO_DATE_TIME);

        var schedule = new EqualsSchedule().setScale(Scale.YEAR).setValue(2021);

        boolean match = matcher.match(new MatchingContext(new ScheduleMatchersRegistry(), schedule, dateTime));

        Assertions.assertFalse(match);
    }

}
