package org.kuneberg.geosatis.schedules.rest;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kuneberg.geosatis.schedules.Application;
import org.kuneberg.geosatis.schedules.model.ScheduleEntity;
import org.kuneberg.geosatis.schedules.model.schedule.EqualsSchedule;
import org.kuneberg.geosatis.schedules.model.schedule.Scale;
import org.kuneberg.geosatis.schedules.service.ScheduleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ScheduleEntityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ScheduleEntityService scheduleEntityService;

    @Test
    void testCreateSchedule() throws Exception {
        mockMvc.perform(post("/offenders/{offenderId}/zones/{zoneId}/schedule", "1", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "schedule": {
                                "type": "eq",
                                "scale": "YEAR",
                                "value": 2022
                            }
                        }
                        """)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.is(true)));
    }

    @Test
    void testGetSchedule() throws Exception {
        mockMvc.perform(get("/offenders/{offenderId}/zones/{zoneId}/schedule", "1", "1")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.is(true)))
                .andExpect(jsonPath("$.data.zoneId", Matchers.is("1")));
    }

    @Test
    void testMatchSchedule() throws Exception {
        var dateTime = LocalDateTime.now();

        var entity = new ScheduleEntity()
                .setOffenderId("1")
                .setZoneId("1")
                .setSchedule(new EqualsSchedule().setScale(Scale.YEAR).setValue(dateTime.getYear()));
        scheduleEntityService.save(entity);


        mockMvc.perform(get("/offenders/{offenderId}/zones/{zoneId}/schedule/matches?dateTime={dateTime}",
                        "1", "1", dateTime.plus(1, ChronoUnit.MINUTES).format(DateTimeFormatter.ISO_DATE_TIME))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.is(true)))
                .andExpect(jsonPath("$.data", Matchers.is(true)));
    }

    @Test
    void testMatchSchedule2() throws Exception {
        var dateTime = LocalDateTime.now();

        var entity = new ScheduleEntity()
                .setOffenderId("1")
                .setZoneId("1")
                .setSchedule(new EqualsSchedule().setScale(Scale.YEAR).setValue(dateTime.getYear()));
        scheduleEntityService.save(entity);


        mockMvc.perform(get("/offenders/{offenderId}/zones/{zoneId}/schedule/matches?dateTime={dateTime}",
                        "1", "1", dateTime.plus(1, ChronoUnit.YEARS).format(DateTimeFormatter.ISO_DATE_TIME))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.is(true)))
                .andExpect(jsonPath("$.data", Matchers.is(false)));
    }

}
