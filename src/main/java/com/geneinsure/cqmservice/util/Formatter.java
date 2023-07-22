package com.geneinsure.cqmservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public class Formatter {
    private static final Logger log = LoggerFactory.getLogger(Formatter.class);

    private static final ObjectMapper prettyPrinter = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule());

    public static String toJson(Object obj) {

        String prettyJsonObj = "";
        if (obj != null) {
            try {
                prettyJsonObj = prettyPrinter.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.error("Failed to convert Object to JSON - {}", e.getMessage(), e);
            }
        }
        return prettyJsonObj;
    }
}
