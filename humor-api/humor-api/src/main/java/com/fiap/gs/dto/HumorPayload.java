package com.fiap.gs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public class HumorPayload {

    @JsonProperty("humor")
    private String humor;

    @JsonProperty("timestamp")
    private Instant timestamp;

    public String getHumor() {
        return humor;
    }

    public void setHumor(String humor) {
        this.humor = humor;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "HumorPayload{" +
                "humor='" + humor + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}