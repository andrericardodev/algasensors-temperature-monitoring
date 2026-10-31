package com.algaworks.algasensors.temperature_monitoring.api.config.jackson;

import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class TSIDJacksonConfig {

    @Bean
    public SimpleModule tsidModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(TSID.class, ToStringSerializer.instance);
        return module;
    }

}
