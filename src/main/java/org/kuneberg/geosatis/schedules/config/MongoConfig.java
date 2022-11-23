package org.kuneberg.geosatis.schedules.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConditionalOnProperty(value = "dao.stub", havingValue = "false")
public class MongoConfig {
    @Bean
    public MongoClient mongoClient(@Value("${mongo.url:27017}") String host) {
        return MongoClients.create(host);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient client, @Value("${mongo.db.name:schedules}") String dbName) {
        return new MongoTemplate(client, dbName);
    }
}
