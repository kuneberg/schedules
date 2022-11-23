package org.kuneberg.geosatis.schedules.model;

import org.kuneberg.geosatis.schedules.model.schedule.Schedule;

import java.time.LocalDateTime;

public class ScheduleEntity {

    private String id;
    private String zoneId;
    private String offenderId;
    private Schedule schedule;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public ScheduleEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getZoneId() {
        return zoneId;
    }

    public ScheduleEntity setZoneId(String zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public String getOffenderId() {
        return offenderId;
    }

    public ScheduleEntity setOffenderId(String offenderId) {
        this.offenderId = offenderId;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public ScheduleEntity setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ScheduleEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
