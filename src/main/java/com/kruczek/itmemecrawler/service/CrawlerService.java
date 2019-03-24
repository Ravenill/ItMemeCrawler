package com.kruczek.itmemecrawler.service;

import com.kruczek.itmemecrawler.mqtt.MqttManager;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    private MqttManager mqttManager;

    public CrawlerService(MqttManager mqttManager) {
        this.mqttManager = mqttManager;
    }

    public void sendTestMessage() {
        mqttManager.sendTestMessage();
    }
}
