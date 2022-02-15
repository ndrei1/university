package com.university.application.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

    private final DataGenerator dataGenerator;

    public RunAfterStartup(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
       dataGenerator.dataGenerate();
    }

}
