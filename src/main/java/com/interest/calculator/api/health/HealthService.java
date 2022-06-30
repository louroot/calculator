package com.interest.calculator.api.health;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HealthService {
    private static final Logger logger = LoggerFactory.getLogger(HealthService.class);
    private final DataSource dataSource;
    
    public HealthCheck dbCheck() {
        logger.info("Checking db health");
        HealthCheck.HealthCheckBuilder dbHealthCheckBuilder = HealthCheck.builder()
            .name("Database health-check")
            .description("DB connection health-check");
        try(Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1)) {
                logger.info("Db is ok, returning health-check");
                return dbHealthCheckBuilder.status(Status.OK).build();
            }
        } catch (Exception e) {
            logger.error("Unexpected exception during db health-check", e);
        }
        return dbHealthCheckBuilder.status(Status.CRITICAL).build();
    }
}
