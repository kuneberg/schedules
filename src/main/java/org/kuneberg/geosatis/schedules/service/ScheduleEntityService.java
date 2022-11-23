package org.kuneberg.geosatis.schedules.service;

import org.kuneberg.geosatis.schedules.dao.ScheduleEntityDao;
import org.kuneberg.geosatis.schedules.model.RW;
import org.kuneberg.geosatis.schedules.model.ScheduleEntity;
import org.kuneberg.geosatis.schedules.service.schedule.MatchingContext;
import org.kuneberg.geosatis.schedules.service.schedule.ScheduleMatchersRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleEntityService {

    private final ScheduleEntityDao scheduleEntityDao;
    private final ScheduleMatchersRegistry registry;

    public ScheduleEntityService(ScheduleEntityDao scheduleEntityDao, ScheduleMatchersRegistry registry) {
        this.scheduleEntityDao = scheduleEntityDao;
        this.registry = registry;
    }

    public RW<ScheduleEntity> save(String offenderId, String zoneId, ScheduleEntity schedule) {
        schedule.setOffenderId(offenderId);
        schedule.setZoneId(zoneId);
        return save(schedule);
    }

    public RW<ScheduleEntity> save(ScheduleEntity schedule) {
        return RW.ok(scheduleEntityDao.save(schedule));
    }


    public RW<ScheduleEntity> get(String offenderId, String zoneId) {
        var zoneSchedule = scheduleEntityDao.findByOffenderIdAndZoneId(offenderId, zoneId);
        return RW.ok(zoneSchedule);
    }

    public RW<Boolean> matches(String offenderId, String zoneId, LocalDateTime dateTime) {
        var zoneSchedule = scheduleEntityDao.findByOffenderIdAndZoneIdAndDateTime(offenderId, zoneId, dateTime);
        return Optional.ofNullable(zoneSchedule)
                .map(ScheduleEntity::getSchedule)
                .map(s -> registry.get(s).match(new MatchingContext(registry, s, dateTime)))
                .map(RW::ok)
                .orElseGet(() -> RW.ok(false));
    }
}
