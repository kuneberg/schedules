package org.kuneberg.geosatis.schedules.config;

import org.kuneberg.geosatis.schedules.service.schedule.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ScheduleConfig {


    @Bean
    public EqualsScheduleMatcher equalsScheduleMatcher() {
        return new EqualsScheduleMatcher();
    }

    @Bean
    public PeriodicScheduleMatcher periodicScheduleMatcher() {
        return new PeriodicScheduleMatcher();
    }

    @Bean
    public RangeScheduleMatcher rangeScheduleMatcher() {
        return new RangeScheduleMatcher();
    }

    @Bean
    public CompositeScheduleMatcher compositeScheduleMatcher() {
        return new CompositeScheduleMatcher();
    }

    @Bean
    public ScheduleMatchersRegistry scheduleMatchersRegistry(List<ScheduleMatcher> matchers) {
        return new ScheduleMatchersRegistry(matchers);
    }
}
