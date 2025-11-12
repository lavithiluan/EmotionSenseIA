package com.fiap.gs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.gs.dto.HumorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MqttHumorSubscriber {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GenerativeAiService aiService;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleHumorMessage(@Payload String jsonPayload) {

        System.out.println("[MQTT RECEBIDO] JSON: " + jsonPayload);

        try {
            HumorPayload payload = objectMapper.readValue(jsonPayload, HumorPayload.class);
            System.out.println("Humor: " + payload.getHumor());
            System.out.println("Timestamp: " + payload.getTimestamp());
            aiService.gerarDica(payload.getHumor());
            System.out.println("[MQTT] Mensagem processada. Aguardando a próxima.");

        } catch (Exception e) {
            System.err.println("Falha ao ler o JSON do MQTT: " + e.getMessage());
        }
    }
}