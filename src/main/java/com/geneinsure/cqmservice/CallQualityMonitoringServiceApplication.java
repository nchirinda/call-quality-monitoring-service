package com.geneinsure.cqmservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CallQualityMonitoringServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(CallQualityMonitoringServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CallQualityMonitoringServiceApplication.class, args);

        log.info("[=]==========[ CallQualityMonitoring Service Started ]==========[=]");
    }
}
