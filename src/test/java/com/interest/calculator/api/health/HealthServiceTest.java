package com.interest.calculator.api.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class HealthServiceTest {
    public static final String DB_HEALTH_CHECK_NAME = "Database health-check";
    public static final String DB_HEALTH_CHECK_DESCRIPTION = "DB connection health-check";
    @Mock
    DataSource dataSource;

    @Mock
    Connection connection;

    @InjectMocks
    HealthService healthService;

    @BeforeEach
    void setUp() throws SQLException {
        openMocks(this);
        when(dataSource.getConnection()).thenReturn(connection);
    }

    @Test
    public void dbCheck() throws SQLException {
        when(connection.isValid(anyInt())).thenReturn(true);
        HealthCheck result = healthService.dbCheck();
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(DB_HEALTH_CHECK_NAME);
        assertThat(result.getDescription()).isEqualTo(DB_HEALTH_CHECK_DESCRIPTION);
        assertThat(result.getStatus().getHttpStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void dbCheckFail() throws SQLException {
        when(connection.isValid(anyInt())).thenReturn(false);
        HealthCheck result = healthService.dbCheck();
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(DB_HEALTH_CHECK_NAME);
        assertThat(result.getDescription()).isEqualTo(DB_HEALTH_CHECK_DESCRIPTION);
        assertThat(result.getStatus().getHttpStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void dbCheckException() throws SQLException {
        when(dataSource.getConnection()).thenThrow(new SQLException("db exception"));
        HealthCheck result = healthService.dbCheck();
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(DB_HEALTH_CHECK_NAME);
        assertThat(result.getDescription()).isEqualTo(DB_HEALTH_CHECK_DESCRIPTION);
        assertThat(result.getStatus().getHttpStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}