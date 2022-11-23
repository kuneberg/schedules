package org.kuneberg.geosatis.schedules.dao;

import org.kuneberg.geosatis.schedules.model.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@ConditionalOnProperty(value = "dao.stub", havingValue = "false")
public class MongoScheduleEntityDao
        implements ScheduleEntityDao
{

    public static final String COLLECTION_NAME = "schedules";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoScheduleEntityDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ScheduleEntity save(ScheduleEntity schedule) {
        schedule.setCreatedAt(LocalDateTime.now());
        return mongoTemplate.save(schedule, COLLECTION_NAME);
    }

    @Override
    public ScheduleEntity findByOffenderIdAndZoneIdAndDateTime(String offenderId, String zoneId, LocalDateTime dateTime) {
        var query = Query.query(Criteria
                .where("offenderId").is(offenderId)
                .and("zoneId").is(zoneId)
                .and("createdAt").gte(dateTime)
        ).with(Sort.by("createdAt", "-1")).limit(1);
        return mongoTemplate.findOne(query,
                ScheduleEntity.class, COLLECTION_NAME);
    }

    @Override
    public ScheduleEntity findByOffenderIdAndZoneId(String offenderId, String zoneId) {
        var query = Query.query(Criteria.where("offenderId").is(offenderId).and("zoneId").is(zoneId))
                .with(Sort.by("createdAt", "-1")).limit(1);
        return mongoTemplate.findOne(query,
                ScheduleEntity.class, COLLECTION_NAME);
    }
}
