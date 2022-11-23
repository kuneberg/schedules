package org.kuneberg.geosatis.schedules.service.schedule;

import org.kuneberg.geosatis.schedules.model.schedule.Schedule;

public interface ScheduleMatcher<T extends Schedule> {

    Class<T> getAssignableFor();


    boolean match(MatchingContext<T> ctx);

}
