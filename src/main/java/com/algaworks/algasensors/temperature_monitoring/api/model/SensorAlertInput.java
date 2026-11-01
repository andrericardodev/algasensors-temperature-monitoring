package com.algaworks.algasensors.temperature_monitoring.api.model;

import lombok.Data;

@Data
public class SensorAlertInput {

    private Double maxTemperature;
    private Double minTemperature;
}
