package com.algaworks.algasensors.temperature_monitoring.api.config.web;

import io.hypersistence.tsid.TSID;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;

public class StringToTSIDWebConverter implements Converter<String, TSID> {

    @Override
    public TSID convert(@NonNull String source) {
        return TSID.from(source);
    }

}
