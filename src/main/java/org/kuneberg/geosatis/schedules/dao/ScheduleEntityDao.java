package org.kuneberg.geosatis.schedules.dao;

import org.kuneberg.geosatis.schedules.model.ScheduleEntity;

import java.time.LocalDateTime;

public interface ScheduleEntityDao {

    ScheduleEntity save(ScheduleEntity schedule);

    ScheduleEntity findByOffenderIdAndZoneIdAndDateTime(String offenderId, String zoneId, LocalDateTime dateTime);
    ScheduleEntity findByOffenderIdAndZoneId(String offenderId, String zoneId);


}
