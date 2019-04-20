package com.kruczek.itmemecrawler.service;

import com.kruczek.itmemecrawler.sender.MqttSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    private MqttSender mqttManager;

    @Autowired
    public CrawlerService(MqttSender mqttManager) {
        this.mqttManager = mqttManager;
    }

    public void sendTestMessage() {
        mqttManager.send("https://qph.fs.quoracdn.net/main-qimg-d8e74079479528a01e27c5daf10529cc.webp");
    }
}
