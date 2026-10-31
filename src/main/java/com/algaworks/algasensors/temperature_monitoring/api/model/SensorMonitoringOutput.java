package com.algaworks.algasensors.temperature_monitoring.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOutput {
    private TSID sensorId;
    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enabled;
}
