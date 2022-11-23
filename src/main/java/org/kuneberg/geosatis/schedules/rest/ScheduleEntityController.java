package org.kuneberg.geosatis.schedules.rest;

import org.kuneberg.geosatis.schedules.model.RW;
import org.kuneberg.geosatis.schedules.model.ScheduleEntity;
import org.kuneberg.geosatis.schedules.service.ScheduleEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ScheduleEntityController {

    private final ScheduleEntityService service;

    public ScheduleEntityController(ScheduleEntityService service) {
        this.service = service;
    }

    @PostMapping("/offenders/{offenderId}/zones/{zoneId}/schedule")
    public RW<ScheduleEntity> save(@PathVariable("offenderId") String offenderId,
                                   @PathVariable("zoneId") String zoneId,
                                   @RequestBody ScheduleEntity schedule) {
        return service.save(offenderId, zoneId, schedule);
    }

    @GetMapping("/offenders/{offenderId}/zones/{zoneId}/schedule")
    public RW<ScheduleEntity> get(@PathVariable("offenderId") String offenderId,
                                  @PathVariable("zoneId") String zoneId) {
        return service.get(offenderId, zoneId);
    }

    @GetMapping("/offenders/{offenderId}/zones/{zoneId}/schedule/matches")
    public RW<Boolean> matches(@PathVariable("offenderId") String offenderId,
                               @PathVariable("zoneId") String zoneId,
                               @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return service.matches(offenderId, zoneId, dateTime);
    }
}
