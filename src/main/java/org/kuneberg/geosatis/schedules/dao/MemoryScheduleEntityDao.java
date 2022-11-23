package org.kuneberg.geosatis.schedules.dao;

import org.kuneberg.geosatis.schedules.model.ScheduleEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ConditionalOnProperty(value = "dao.stub", havingValue = "true")
public class MemoryScheduleEntityDao implements ScheduleEntityDao {
    private final Map<String, List<ScheduleEntity>> schedules;

    public MemoryScheduleEntityDao() {
        schedules = new ConcurrentHashMap<>();
    }

    @Override
    public ScheduleEntity save(ScheduleEntity schedule) {
        String key = getKey(schedule.getOffenderId(), schedule.getZoneId());
        var schedules = this.schedules.computeIfAbsent(key, k -> new ArrayList<>());
        synchronized (schedules) {
            schedule.setCreatedAt(LocalDateTime.now());
            schedules.add(schedule);
        }
        return schedule;
    }

    @Override
    public ScheduleEntity findByOffenderIdAndZoneIdAndDateTime(String offenderId, String zoneId, LocalDateTime dateTime) {
        String key = getKey(offenderId, zoneId);
        return Optional.ofNullable(schedules.get(key))
                .flatMap(l -> l.stream().filter(s -> s.getCreatedAt().isBefore(dateTime)).max(Comparator.comparing(ScheduleEntity::getCreatedAt)))
                .orElse(null);
    }

    @Override
    public ScheduleEntity findByOffenderIdAndZoneId(String offenderId, String zoneId) {
        String key = getKey(offenderId, zoneId);
        return Optional.ofNullable(schedules.get(key))
                .flatMap(l -> l.stream().max(Comparator.comparing(ScheduleEntity::getCreatedAt)))
                .orElse(null);
    }

    private String getKey(String offenderId, String zoneId) {
        var key = offenderId + "-" + zoneId;
        return key;
    }
}
