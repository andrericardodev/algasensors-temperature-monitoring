package com.algaworks.algasensors.temperature_monitoring.api.controller;

import com.algaworks.algasensors.temperature_monitoring.api.model.SensorAlertInput;
import com.algaworks.algasensors.temperature_monitoring.api.model.SensorAlertOutput;
import com.algaworks.algasensors.temperature_monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature_monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature_monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository sensorAlertRepository;

    @GetMapping
    public SensorAlertOutput getDetail(@PathVariable TSID sensorId) {
        return toOutput(findByIdOrThrow(sensorId));
    }

    @PutMapping
    public SensorAlertOutput save(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input) {
        SensorId id = new SensorId(sensorId);
        SensorAlert sensorAlert = sensorAlertRepository.findById(id)
                .orElseGet(() -> SensorAlert.builder().id(id).build());

        sensorAlert.setMaxTemperature(input.getMaxTemperature());
        sensorAlert.setMinTemperature(input.getMinTemperature());

        return toOutput(sensorAlertRepository.saveAndFlush(sensorAlert));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        SensorAlert sensorAlert = findByIdOrThrow(sensorId);
        sensorAlertRepository.delete(sensorAlert);
    }

    private SensorAlert findByIdOrThrow(TSID sensorId) {
        return sensorAlertRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private SensorAlertOutput toOutput(SensorAlert sensorAlert) {
        return SensorAlertOutput.builder()
                .id(sensorAlert.getId().getValue())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
    }
}
